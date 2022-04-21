package edu.gatech.streamingwars.systemservices;

import edu.gatech.streamingwars.crudservices.*;
import edu.gatech.streamingwars.models.Event;
import edu.gatech.streamingwars.models.Offer;
import edu.gatech.streamingwars.models.StreamingService;
import edu.gatech.streamingwars.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private EventCRUDService eventCRUDService;

    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;

    // @Autowired
    // private DemographicGroupCRUDService demographicGroupCRUDService;

    @Autowired
    private StudioCRUDService studioCRUDService;

    @Autowired
    private OfferCRUDService offerCRUDService;


    public Offer saveOffer(Offer offer) {

        // get streaming service
        StreamingService streamingService = streamingServiceCRUDService.findByShortName(offer.getStreamingServiceName());

        // get event
        Event event = eventCRUDService.findByNameAndYearProduced(offer.getEventName(), offer.getYearProduced());

        // validation, move it to the controller level?
        if (streamingService == null) {
            offer.setPrice(-1);
            return offer;
        }
        if (event == null) {
            offer.setPrice(-2);
            return offer;
        }

        // get producing studio
        Studio studio = studioCRUDService.getStudio(event.getProduceId());
        // Studio studio = studioCRUDService.findByShortName(event.getStudioShortName());

        //validation, move it to the controller level?
        if (studio == null) {
            offer.setPrice(-3);
            return offer;
        }

        // check if offer exists
        Offer found = offerCRUDService.findByStreamingServiceIdAndEventId(streamingService.getId(), event.getId());
        if (found != null) return found; //duplicate

        offer.setStreamingServiceId(streamingService.getId());
        offer.setEventId(event.getId());

        //mark event is being offered
        event.setOffered(true);
        eventCRUDService.updateEvent(event.getId(), event);

        // update LicenceFee collection
        streamingService.payLicenseFee(event);
        streamingServiceCRUDService.updateStreamingService(streamingService.getId(), streamingService);

        studio.collectLicenseFee(event);
        studioCRUDService.updateStudio(studio.getId(), studio);

        //save to repo
        return offerCRUDService.saveOffer(offer);
    }

    public Offer getOffer(Long id) {
        return offerCRUDService.getOffer(id);
    }
    public List<Offer> getOffers() {
        return offerCRUDService.getOffers();
    }

    public Offer updateOffer(Long id, Offer offer) {
        return offerCRUDService.updateOffer(id, offer);
    }

    public void deleteOffer(Long id) {
        offerCRUDService.deleteOffer(id);
    }

    public Offer findByStreamingServiceNameAndEventNameAndYearProduced(String streamingServiceName, String eventName, String yearProduced) {
        return offerCRUDService.findByStreamingServiceNameAndEventNameAndYearProduced(streamingServiceName,
                eventName,
                yearProduced);
    }

    // // merge two methods to one
    // public Offer OfferEvent(String stream_name, String event_name, String year_produced) {
    //     //get and validate event
    //     Event event = eventCRUDService.findByNameAndYearProduced(event_name, year_produced);
    //     if (event == null) {
    //         throw new IllegalArgumentException("Event not exist for offering!");
    //     }
    //     if (!event.getType().equals("movie")) {
    //         throw new IllegalArgumentException("Want to offer as movie, but initial type is not move!");
    //     }
    //
    //     // get streaming service
    //     StreamingService streamingService = streamingServiceCRUDService.findByShortName(stream_name);
    //     if (streamingService == null) {
    //         throw new IllegalArgumentException("StreamingService not exist!");
    //     }
    //
    //     // get producing studio
    //     Studio studio = studioCRUDService.getStudio(event.getProduceId());
    //
    //     // offer event
    //     Offer offer = offerCRUDService.findByStreamingServiceIdAndEventId(streamingService.getId(), event.getId());
    //     if (offer != null) return offer; //duplicate
    //
    //     offer = new Offer(streamingService.getId(), event.getId());
    //     offerCRUDService.saveOffer(offer);
    //
    //     //?? save to db
    //     streamingService.payLicenseFee(event);
    //     streamingServiceCRUDService.updateStreamingService(streamingService.getId(), streamingService);
    //
    //     studio.collectLicenseFee(event);
    //     studioCRUDService.updateStudio(studio.getId(), studio);
    //     return offer;
    // }
    //
    // public Offer OfferEvent(String stream_name, String event_name, String year_produced, int price) {
    //     Event event = eventCRUDService.findByNameAndYearProduced(event_name, year_produced);
    //     if (event == null) {
    //         throw new IllegalArgumentException("Event not exist for offering!");
    //     }
    //     if (!event.getType().equals("ppv")) {
    //         throw new IllegalArgumentException("Want to offer as ppv, but initial type is not ppv!");
    //     }
    //
    //     StreamingService streamingService = streamingServiceCRUDService.findByShortName(stream_name);
    //     if (streamingService == null) {
    //         throw new IllegalArgumentException("StreamingService not exist!");
    //     }
    //
    //     // get producing studio
    //     Studio studio = studioCRUDService.getStudio(event.getProduceId());
    //
    //     // offer event
    //     Offer offer = offerCRUDService.findByStreamingServiceIdAndEventId(streamingService.getId(), event.getId());
    //     if (offer != null) return offer; //duplicate
    //
    //     offer = new Offer(streamingService.getId(), event.getId(), price);
    //     offerCRUDService.saveOffer(offer);
    //
    //     //?? save to db
    //     streamingService.payLicenseFee(event);
    //     streamingServiceCRUDService.updateStreamingService(streamingService.getId(), streamingService);
    //
    //     studio.collectLicenseFee(event);
    //     studioCRUDService.updateStudio(studio.getId(), studio);
    //     return offer;
    // }

}
