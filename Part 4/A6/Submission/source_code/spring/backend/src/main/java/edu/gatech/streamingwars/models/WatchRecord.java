package edu.gatech.streamingwars.models;

import javax.persistence.*;

@Entity
public class WatchRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String demographicGroupShortName;
    private Integer percentage;
    private String streamingServiceShortName;
    private String eventName;
    private String yearProduced;
    private long streamingServiceId;




    public WatchRecord(long streamingServiceId, Integer percentage) {
        this.streamingServiceId = streamingServiceId;
        this.percentage = percentage;
    }

    public WatchRecord(String demographicGroupShortName, Integer percentage, String streamingServiceShortName, String eventName, String yearProduced) {
        this.demographicGroupShortName = demographicGroupShortName;
        this.percentage = percentage;
        this.streamingServiceShortName = streamingServiceShortName;
        this.eventName = eventName;
        this.yearProduced = yearProduced;
    }

    public WatchRecord() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDemographicGroupShortName() {
        return demographicGroupShortName;
    }

    public void setDemographicGroupShortName(String demographicGroupShortName) {
        this.demographicGroupShortName = demographicGroupShortName;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getStreamingServiceShortName() {
        return streamingServiceShortName;
    }

    public void setStreamingServiceShortName(String streamingServiceShortName) {
        this.streamingServiceShortName = streamingServiceShortName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(String yearProduced) {
        this.yearProduced = yearProduced;
    }

    public long getStreamingServiceId() {
        return streamingServiceId;
    }

    public void setStreamingServiceId(long streamingServiceId) {
        this.streamingServiceId = streamingServiceId;
    }
}
