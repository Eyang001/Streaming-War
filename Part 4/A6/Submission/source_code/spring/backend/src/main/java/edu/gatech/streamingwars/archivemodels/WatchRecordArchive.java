package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class WatchRecordArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long originId;
    private String demographicGroupShortName;
    private Integer percentage;
    private String streamingServiceShortName;
    private String eventName;
    private String yearProduced;
    private long streamingServiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public void setId(long id) {
        this.id = id;
    }

    public WatchRecordArchive() {
    }

    public WatchRecordArchive(long originId, String demographicGroupShortName, Integer percentage, String streamingServiceShortName, String eventName, String yearProduced, long streamingServiceId) {
        this.originId = originId;
        this.demographicGroupShortName = demographicGroupShortName;
        this.percentage = percentage;
        this.streamingServiceShortName = streamingServiceShortName;
        this.eventName = eventName;
        this.yearProduced = yearProduced;
        this.streamingServiceId = streamingServiceId;
    }

    public void setDemographicGroupShortName(String demographicGroupShortName) {
        this.demographicGroupShortName = demographicGroupShortName;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public void setStreamingServiceShortName(String streamingServiceShortName) {
        this.streamingServiceShortName = streamingServiceShortName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setYearProduced(String yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setStreamingServiceId(long streamingServiceId) {
        this.streamingServiceId = streamingServiceId;
    }

    public void setArchiveData(ArchiveData archiveData) {
        this.archiveData = archiveData;
    }

    // public long getId() {
    //     return id;
    // }

    public long getOriginId() {
        return originId;
    }

    public String getDemographicGroupShortName() {
        return demographicGroupShortName;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getStreamingServiceShortName() {
        return streamingServiceShortName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public long getStreamingServiceId() {
        return streamingServiceId;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }
}
