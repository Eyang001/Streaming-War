package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class StreamingServiceArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long originId;
    private String shortName;
    private String longName;
    private int subscriptionPrice;
    private int ltd_rev;
    private int prev_mon_rev;
    private int current_mon_rev;
    private int ltd_license_fee;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public StreamingServiceArchive() {
    }

    public StreamingServiceArchive(long originId, String shortName, String longName, int subscriptionPrice, int ltd_rev, int prev_mon_rev, int current_mon_rev, int ltd_license_fee) {
        this.originId = originId;
        this.shortName = shortName;
        this.longName = longName;
        this.subscriptionPrice = subscriptionPrice;
        this.ltd_rev = ltd_rev;
        this.prev_mon_rev = prev_mon_rev;
        this.current_mon_rev = current_mon_rev;
        this.ltd_license_fee = ltd_license_fee;
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

    public void setSubscriptionPrice(int subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public void setLtd_rev(int ltd_rev) {
        this.ltd_rev = ltd_rev;
    }

    public void setPrev_mon_rev(int prev_mon_rev) {
        this.prev_mon_rev = prev_mon_rev;
    }

    public void setCurrent_mon_rev(int current_mon_rev) {
        this.current_mon_rev = current_mon_rev;
    }

    public void setLtd_license_fee(int ltd_license_fee) {
        this.ltd_license_fee = ltd_license_fee;
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

    public int getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public int getLtd_rev() {
        return ltd_rev;
    }

    public int getPrev_mon_rev() {
        return prev_mon_rev;
    }

    public int getCurrent_mon_rev() {
        return current_mon_rev;
    }

    public int getLtd_license_fee() {
        return ltd_license_fee;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }
}
