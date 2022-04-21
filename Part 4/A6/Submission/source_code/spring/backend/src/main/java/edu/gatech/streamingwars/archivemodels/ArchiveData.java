package edu.gatech.streamingwars.archivemodels;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ArchiveData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;

    private String timestamp;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<DemographicGroupArchive> demographicGroupsArchive = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<EventArchive> eventsArchive = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<StreamingServiceArchive> streamingServicesArchive = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<StudioArchive> studiosArchive = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<OfferArchive> offersArchive = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archiveData")
    private List<WatchRecordArchive> watchRecordsArchive = new ArrayList<>();

    public ArchiveData() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDemographicGroupsArchive(List<DemographicGroupArchive> demographicGroupsArchive) {
        this.demographicGroupsArchive = demographicGroupsArchive;
    }

    public void setEventsArchive(List<EventArchive> eventsArchive) {
        this.eventsArchive = eventsArchive;
    }

    public void setStreamingServicesArchive(List<StreamingServiceArchive> streamingServicesArchive) {
        this.streamingServicesArchive = streamingServicesArchive;
    }

    public void setStudiosArchive(List<StudioArchive> studiosArchive) {
        this.studiosArchive = studiosArchive;
    }

    public void setOffersArchive(List<OfferArchive> offersArchive) {
        this.offersArchive = offersArchive;
    }

    public void setWatchRecordsArchive(List<WatchRecordArchive> watchRecordsArchive) {
        this.watchRecordsArchive = watchRecordsArchive;
    }

    public long getId() {
        return id;
    }

    public List<DemographicGroupArchive> getDemographicGroupsArchive() {
        return demographicGroupsArchive;
    }

    public List<EventArchive> getEventsArchive() {
        return eventsArchive;
    }

    public List<StreamingServiceArchive> getStreamingServicesArchive() {
        return streamingServicesArchive;
    }

    public List<StudioArchive> getStudiosArchive() {
        return studiosArchive;
    }

    public List<OfferArchive> getOffersArchive() {
        return offersArchive;
    }

    public List<WatchRecordArchive> getWatchRecordsArchive() {
        return watchRecordsArchive;
    }
}
