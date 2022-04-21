package edu.gatech.streamingwars.models;

import javax.persistence.*;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String streamingServiceName;
    private String eventName;
    private String yearProduced;
    private int price;

    private long streamingServiceId;
    private long eventId;



    public Offer(long streamingServiceId, long eventId) {
        this.streamingServiceId = streamingServiceId;
        this.eventId = eventId;
        this.price = 0;
    }

    public Offer(long streamingServiceId, long eventId, int price) {
        this.streamingServiceId = streamingServiceId;
        this.eventId = eventId;
        this.price = price;
    }

    public Offer(String streamingServiceName, String eventName, String yearProduced) {
        this.streamingServiceName = streamingServiceName;
        this.eventName = eventName;
        this.yearProduced = yearProduced;
    }

    public Offer(String streamingServiceName, String eventName, String yearProduced, int price) {
        this.streamingServiceName = streamingServiceName;
        this.eventName = eventName;
        this.yearProduced = yearProduced;
        this.price = price;
    }

    public Offer(){}

    public Long getId() {
        return id;
    }

    public long getStreamingServiceId() {
        return streamingServiceId;
    }

    public long getEventId() {
        return eventId;
    }

    public int getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreamingServiceId(long streamingServiceId) {
        this.streamingServiceId = streamingServiceId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStreamingServiceName() {
        return streamingServiceName;
    }

    public void setStreamingServiceName(String streamingServiceName) {
        this.streamingServiceName = streamingServiceName;
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
}

