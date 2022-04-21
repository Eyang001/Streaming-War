package edu.gatech.streamingwars.systemservices;

import edu.gatech.streamingwars.archiveRepositories.*;
import edu.gatech.streamingwars.archivemodels.*;
import edu.gatech.streamingwars.crudservices.*;
import edu.gatech.streamingwars.models.*;
import edu.gatech.streamingwars.repositories.WatchServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private DemographicGroupArchiveRepository demographicGroupArchiveRepository;
    @Autowired
    private EventArchiveRepository eventArchiveRepository;
    @Autowired
    private OfferArchiveRepository offerArchiveRepository;
    @Autowired
    private StreamingServiceArchiveRepository streamingServiceArchiveRepository;
    @Autowired
    private StudioArchiveRepository studioArchiveRepository;
    @Autowired
    private WatchServiceArchiveRepository watchServiceArchiveRepository;


    @Autowired
    private DemographicGroupCRUDService demographicGroupCRUDService;
    @Autowired
    private EventCRUDService eventCRUDService;
    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;
    @Autowired
    private StudioCRUDService studioCRUDService;
    @Autowired
    private OfferCRUDService offerCRUDService;
    @Autowired
    private WatchServiceRepository watchServiceRepository;

    //test,test,test
    public ArchiveData archive(String timestamp) {
        ArchiveData found = archiveRepository.findByTimestamp(timestamp);
        if (found != null) {
            return found;
        }
        // implement
        ArchiveData archiveData = new ArchiveData();
        archiveData.setTimestamp(timestamp);
        // //demographicGroups
        List<DemographicGroupArchive> list = new ArrayList<>();
        for (DemographicGroup row : demographicGroupCRUDService.getDemographicGroups()) {
            list.add(new DemographicGroupArchive(
                    row.getId(),
                    row.getShortName(),
                    row.getLongName(),
                    row.getNumber_of_accounts(),
                    row.getLtd_spending(),
                    row.getPrev_mon_spending(),
                    row.getCurrent_mon_spending()
            ));
        }
        archiveData.setDemographicGroupsArchive(list);
        //events
        List<EventArchive> list1 = new ArrayList<>();
        for (Event row : eventCRUDService.getEvents()) {
            list1.add(new EventArchive(
                    row.getId(),
                    row.getType(),
                    row.getName(),
                    row.getYearProduced(),
                    row.getDuration(),
                    row.getStudioShortName(),
                    row.getLicensingFee(),
                    row.getProduceId(),
                    row.isOffered(),
                    row.isWatched()
            ));
        }
        archiveData.setEventsArchive(list1);
        // streaming service
        List<StreamingServiceArchive> list2 = new ArrayList<>();
        for (StreamingService row : streamingServiceCRUDService.getStreamingServices()) {
            list2.add(new StreamingServiceArchive(
                    row.getId(),
                    row.getShortName(),
                    row.getLongName(),
                    row.getSubscriptionPrice(),
                    row.getLtd_rev(),
                    row.getPrev_mon_rev(),
                    row.getCurrent_mon_rev(),
                    row.getLtd_license_fee()
            ));
        }
        archiveData.setStreamingServicesArchive(list2);
        //studio
        List<StudioArchive> list3 = new ArrayList<>();
        for (Studio row : studioCRUDService.getStudios()) {
            list3.add(new StudioArchive(
                    row.getId(),
                    row.getShortName(),
                    row.getLongName(),
                    row.getLtd_fee_collected(),
                    row.getPrev_mon_fee_collected(),
                    row.getCurrent_mon_fee_collected()
            ));
        }
        archiveData.setStudiosArchive(list3);
        //offer
        List<OfferArchive> list4 = new ArrayList<>();
        for (Offer row : offerCRUDService.getOffers()) {
            list4.add(new OfferArchive(
                    row.getId(),
                    row.getStreamingServiceName(),
                    row.getEventName(),
                    row.getYearProduced(),
                    row.getPrice(),
                    row.getStreamingServiceId(),
                    row.getEventId()
            ));
        }
        archiveData.setOffersArchive(list4);
        //watch
        List<WatchRecordArchive> list5 = new ArrayList<>();
        for (WatchRecord row : watchServiceRepository.findAll()) {
            list5.add(new WatchRecordArchive(
                    row.getId(),
                    row.getDemographicGroupShortName(),
                    row.getPercentage(),
                    row.getStreamingServiceShortName(),
                    row.getEventName(),
                    row.getYearProduced(),
                    row.getStreamingServiceId()
            ));
        }
        archiveData.setWatchRecordsArchive(list5);
        // archiveRepository.save(archiveData);
        // return archiveData;
        return archiveRepository.save(archiveData);
    }

    public void purge(String timestamp) {
        Long id = archiveRepository.findIdByTimestamp(timestamp);
        if (id == null) return;
        archiveRepository.deleteById(id);
    }
}
