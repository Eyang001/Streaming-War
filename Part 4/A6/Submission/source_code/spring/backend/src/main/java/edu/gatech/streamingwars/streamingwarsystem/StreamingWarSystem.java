package edu.gatech.streamingwars.streamingwarsystem;

import edu.gatech.streamingwars.archivemodels.*;
import edu.gatech.streamingwars.crudservices.*;
import edu.gatech.streamingwars.models.*;
import edu.gatech.streamingwars.repositories.WatchServiceRepository;
import edu.gatech.streamingwars.systemservices.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StreamingWarSystem {
    private int month;
    private int year;

    @Autowired
    private EventCRUDService eventCRUDService;

    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;

    @Autowired
    private DemographicGroupCRUDService demographicGroupCRUDService;

    @Autowired
    private StudioCRUDService studioCRUDService;

    @Autowired
    private OfferCRUDService offerCRUDService;

    @Autowired
    private WatchServiceRepository watchServiceRepository;

    @Autowired
    private ArchiveService archiveService;

    public StreamingWarSystem(){
        this.month = 10;
        this.year = 2020;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Integer> displayTime() {
        return new ArrayList<>(Arrays.asList(year, month));
    }

    public void resetMonth() {
        // this.month = 10;
        // this.year = 2020;
    }

    //[8]
    public ArchiveData nextMonth() {
        //1. Archive current month data to DB
        String current = this.year + "#" + this.month;
        ArchiveData test = archiveService.archive(current);

        //2. purge data older than six month
        String sixMonthbefore = getSixMonthBefore();
        archiveService.purge(sixMonthbefore); // purge, need to check,testtest

        //3. forward time
        if (this.month == 12) { this.month = 1; this.year++; } else { this.month++; }

        //4. reset repository
        resetRepo();
        return test;
    }

    private String getSixMonthBefore() {
        if (month-6 > 0) {
            return this.year + "#" + (this.month-6);
        } else {
            return (this.year-1) + "#" + (12+month-6);
        }
    }

    private void resetRepo() {
        for (DemographicGroup demographicGroup : demographicGroupCRUDService.getDemographicGroups()) {
            demographicGroup.restart();
        }
        for (StreamingService streamingService : streamingServiceCRUDService.getStreamingServices()) {
            streamingService.restart();
        }
        for (Studio studio : studioCRUDService.getStudios()) {
            studio.restart();
        }
        offerCRUDService.deleteAllInBatch();
        watchServiceRepository.deleteAllInBatch();
    }

}

