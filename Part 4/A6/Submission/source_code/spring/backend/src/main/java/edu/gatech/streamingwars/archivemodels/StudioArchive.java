package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class StudioArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long originId;
    private String shortName;
    private String longName;
    // private List<Event> currentListing;
    private int ltd_fee_collected;
    private int prev_mon_fee_collected;
    private int current_mon_fee_collected;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public StudioArchive() {
    }

    public StudioArchive(long originId, String shortName, String longName, int ltd_fee_collected, int prev_mon_fee_collected, int current_mon_fee_collected) {
        this.originId = originId;
        this.shortName = shortName;
        this.longName = longName;
        this.ltd_fee_collected = ltd_fee_collected;
        this.prev_mon_fee_collected = prev_mon_fee_collected;
        this.current_mon_fee_collected = current_mon_fee_collected;
    }

    // public void setId(long id) {
    //     this.id = id;
    // }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setLtd_fee_collected(int ltd_fee_collected) {
        this.ltd_fee_collected = ltd_fee_collected;
    }

    public void setPrev_mon_fee_collected(int prev_mon_fee_collected) {
        this.prev_mon_fee_collected = prev_mon_fee_collected;
    }

    public void setCurrent_mon_fee_collected(int current_mon_fee_collected) {
        this.current_mon_fee_collected = current_mon_fee_collected;
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

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public int getLtd_fee_collected() {
        return ltd_fee_collected;
    }

    public int getPrev_mon_fee_collected() {
        return prev_mon_fee_collected;
    }

    public int getCurrent_mon_fee_collected() {
        return current_mon_fee_collected;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }
}
