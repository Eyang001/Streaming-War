package edu.gatech.streamingwars.systemservices;

import edu.gatech.streamingwars.crudservices.*;
import edu.gatech.streamingwars.models.*;
import edu.gatech.streamingwars.repositories.WatchServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchService {

    @Autowired
    private DemographicGroupCRUDService demographicGroupCRUDService;

    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;

    @Autowired
    private EventCRUDService eventCRUDService;

    @Autowired
    private OfferCRUDService offerCRUDService;

    @Autowired
    private WatchServiceRepository watchServiceRepository;

    public WatchRecord watchEvent(WatchRecord watchRecord) {
        DemographicGroup demographicGroup = demographicGroupCRUDService.findByShortName(watchRecord.getDemographicGroupShortName());
        StreamingService streamingService = streamingServiceCRUDService.findByShortName(watchRecord.getStreamingServiceShortName());
        Event event = eventCRUDService.findByNameAndYearProduced(watchRecord.getEventName(), watchRecord.getYearProduced());

        // if (demographicGroup == null || streamingService == null || event == null) {
        //     watchRecord.setPercentage(-1); // for debug purpose
        //     return watchRecord;
        // }

        //test
        if (demographicGroup == null) {
            watchRecord.setPercentage(-1); // for debug purpose
            return watchRecord;
        }
        if (streamingService == null) {
            watchRecord.setPercentage(-2); // for debug purpose
            return watchRecord;
        }
        if (event == null) {
            watchRecord.setPercentage(-3); // for debug purpose
            return watchRecord;
        }

        // check if this streaming service is offering this event currently
        Offer offer = offerCRUDService.findByStreamingServiceIdAndEventId(streamingService.getId(), event.getId());
        if (offer == null ||
                offer.getStreamingServiceId() != streamingService.getId() ||
                offer.getEventId() != event.getId()) { // Streaming Service not offering this event
            watchRecord.setPercentage(-4); // for debug purpose
            return watchRecord;
            // throw new IllegalArgumentException("Streaming Service not offering this event");
        }
        //set streamingServiceId, after passing validation
        watchRecord.setStreamingServiceId(streamingService.getId());

        //check if exists in repository
        // WatchRecord found = watchServiceRepository.findByStreamingServiceId(streamingService.getId());
        WatchRecord found = watchServiceRepository.findByStreamingServiceIdAndDemographicGroupShortName(
                streamingService.getId(), demographicGroup.getShortName()
        );

        //set event.isWatched to be true here, bc/ no matter how to charge for it, it is already watched at this moment
        event.setWatched(true);
        eventCRUDService.updateEvent(event.getId(), event);

        //update watch record by movie or ppv
        if (event.getType().equals("movie")) {
            int current_percent = 0;
            if (found != null && found.getPercentage() >= watchRecord.getPercentage()) {
                return found;
            } else if (found != null) {
                current_percent = found.getPercentage();
                found.setPercentage(watchRecord.getPercentage());
                watchRecord = updateWatchRecord(found.getId(), found);
            } else {
                watchRecord = watchServiceRepository.save(watchRecord);
            }
            demographicGroup.pay(streamingService, watchRecord.getPercentage() - current_percent); //??
            streamingService.collectFee(watchRecord.getPercentage() - current_percent, event, offer.getPrice(), demographicGroup); //??
        } else if (event.getType().equals("ppv")) {
            //no need to save ppv watch record
            demographicGroup.pay(offer.getPrice(), watchRecord.getPercentage());
            streamingService.collectFee(watchRecord.getPercentage(), event, offer.getPrice(), demographicGroup);
        }
        //update to DB
        demographicGroupCRUDService.updateDemographicGroup(demographicGroup.getId(), demographicGroup);
        streamingServiceCRUDService.updateStreamingService(streamingService.getId(), streamingService);

        return watchRecord;
    }

    public WatchRecord getWatchRecord(Long id) {
        return watchServiceRepository.findById(id).orElse(null);
    }
    public List<WatchRecord> getWatchRecords() {
        return watchServiceRepository.findAll();
    }

    public WatchRecord updateWatchRecord(Long id, WatchRecord watchRecord) {
        WatchRecord found = watchServiceRepository.findById(id).orElse(null);
        if (found == null) {
            watchRecord.setId(id);
            return watchServiceRepository.save(watchRecord);
        }
        //update WatchRecord attributes
        // found.setId(id); //????
        found.setDemographicGroupShortName(watchRecord.getDemographicGroupShortName());
        found.setPercentage(watchRecord.getPercentage());
        found.setStreamingServiceShortName(watchRecord.getStreamingServiceShortName());
        found.setEventName(watchRecord.getEventName());
        found.setYearProduced(watchRecord.getYearProduced());
        found.setStreamingServiceId(watchRecord.getStreamingServiceId());
        watchServiceRepository.save(found);
        return found;
    }

    public void deleteWatchRecord(Long id) {
        watchServiceRepository.deleteById(id);
    }

    public WatchRecord saveWatchRecord(WatchRecord watchRecord) {return null;}

    public void deleteAllInBatch() {
        watchServiceRepository.deleteAllInBatch();
    }

}

/*
//[7]
    public void watch_event(String demo_group, int percentage, String stream_service, String event_name, String yearProduced){
        //validate demo_group, streaming_service and event
        int demoId = validateDemo(demo_group);
        int streamId = validateStream(stream_service);
        int eventId = validateEvent(event_name, yearProduced);

        if(eventId ==-1 || streamId == -1 || demoId == -1){ return; }

        //validate offering at stream_service
        StreamingService curr_stream = stream.get(streamId);
        Event curr_event = event.get(eventId);

        int offerId = validateOffer(curr_stream, curr_event);
        if(offerId == -1) {return;}
        Offer curr_offer = offer.get(offerId);

        DemographicGroup curr_demo = demo.get(demoId);
        curr_demo.watch(percentage,curr_offer);
    }
* */

/*
public void watch(int percentage, Offer offer){
        Event event = offer.getEventId();
        StreamingService streamingService = offer.getStreamingServiceId();


        if(event.getType().equals("movie")){
            if(getCurrentSubscription().containsKey(streamingService)){
                if(getCurrentSubscription().get(streamingService) >= percentage) return;
                else subscribe(offer, percentage);
            }
            else subscribe(offer, percentage);
        } else if (event.getType().equals("ppv")){
            pay(offer.getPrice(), percentage);
            streamingService.collectFee(percentage, offer, this);
        }
    }

    public void subscribe(Offer offer, int percentage){
        Event event = offer.getEventId();
        StreamingService streamingService = offer.getStreamingServiceId();
        int current_percent;
        if(getCurrentSubscription().get(streamingService) == null)  {
            current_percent = 0;
        } else {
            current_percent = getCurrentSubscription().get(streamingService);
        }
        this.currentSubscription.put(streamingService, percentage); //here
        pay(streamingService, percentage - current_percent);
        streamingService.collectFee(percentage - current_percent,offer, this);
    }
* */