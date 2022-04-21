package edu.gatech.streamingwars.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "messageType")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Movie.class, name = "movie"),
        @JsonSubTypes.Type(value=PPV.class, name = "ppv")
})
public abstract class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String type;
    private String name;

    private String yearProduced;

    private int duration;
    private String studioShortName;
    private int licensingFee;
    private Long produceId;

    private boolean isOffered;
    private boolean isWatched;


    public Event(String type, String name, String yearProduced, int duration, String studioShortName, int licensingFee) {
        this.type = type;
        this.name = name;
        this.yearProduced = yearProduced;
        this.duration = duration;
        this.studioShortName = studioShortName;
        this.licensingFee = licensingFee;
        this.produceId = 0L;
        this.isOffered = false;
        this.isWatched = false;
    }

    public Event(String name, String yearProduced) {
        this.name = name;
        this.yearProduced = yearProduced;
    }

    public Event(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public int getDuration() {
        return duration;
    }

    public Long getProduceId() {
        return produceId;
    }

    public int getLicensingFee() {
        return licensingFee;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearProduced(String yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setProduceId(long producedBy) {
        this.produceId = producedBy;
    }

    public void setLicensingFee(int licensingFee) {
        this.licensingFee = licensingFee;
    }

    public String getStudioShortName() {
        return studioShortName;
    }

    public void setStudioShortName(String studioShortName) {
        this.studioShortName = studioShortName;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

    public boolean isOffered() {
        return isOffered;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }
}

