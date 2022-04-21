package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class OfferArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private long originId;
    private String streamingServiceName;
    private String eventName;
    private String yearProduced;
    private int price;

    private long streamingServiceId;
    private long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public OfferArchive() {
    }

    public OfferArchive(long originId, String streamingServiceName, String eventName, String yearProduced, int price, long streamingServiceId, long eventId) {
        this.originId = originId;
        this.streamingServiceName = streamingServiceName;
        this.eventName = eventName;
        this.yearProduced = yearProduced;
        this.price = price;
        this.streamingServiceId = streamingServiceId;
        this.eventId = eventId;
        // this.archiveData = archiveData;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public void setStreamingServiceName(String streamingServiceName) {
        this.streamingServiceName = streamingServiceName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setYearProduced(String yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStreamingServiceId(long streamingServiceId) {
        this.streamingServiceId = streamingServiceId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public void setArchiveData(ArchiveData archiveData) {
        this.archiveData = archiveData;
    }

    // public Long getId() {
    //     return id;
    // }

    public long getOriginId() {
        return originId;
    }

    public String getStreamingServiceName() {
        return streamingServiceName;
    }

    public String getEventName() {
        return eventName;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public int getPrice() {
        return price;
    }

    public long getStreamingServiceId() {
        return streamingServiceId;
    }

    public long getEventId() {
        return eventId;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }

}
