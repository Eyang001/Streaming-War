package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class DemographicGroupArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long originId;
    private String shortName;
    private String longName;
    private int number_of_accounts;

    private int ltd_spending;
    private int prev_mon_spending;
    private int current_mon_spending;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public DemographicGroupArchive(long originId,
                                   String shortName,
                                   String longName,
                                   int number_of_accounts,
                                   int ltd_spending,
                                   int prev_mon_spending,
                                   int current_mon_spending) {
        this.originId = originId;
        this.shortName = shortName;
        this.longName = longName;
        this.number_of_accounts = number_of_accounts;
        this.ltd_spending = ltd_spending;
        this.prev_mon_spending = prev_mon_spending;
        this.current_mon_spending = current_mon_spending;
        // this.archiveData = archiveData;
    }

    public DemographicGroupArchive() {
    }

    // public void setId(long id) {
    //     this.id = id;
    // }


    public long getOriginId() {
        return originId;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setNumber_of_accounts(int number_of_accounts) {
        this.number_of_accounts = number_of_accounts;
    }

    public void setLtd_spending(int ltd_spending) {
        this.ltd_spending = ltd_spending;
    }

    public void setPrev_mon_spending(int prev_mon_spending) {
        this.prev_mon_spending = prev_mon_spending;
    }

    public void setCurrent_mon_spending(int current_mon_spending) {
        this.current_mon_spending = current_mon_spending;
    }

    public void setArchiveData(ArchiveData archiveData) {
        this.archiveData = archiveData;
    }

    // public long getId() {
    //     return id;
    // }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public int getNumber_of_accounts() {
        return number_of_accounts;
    }

    public int getLtd_spending() {
        return ltd_spending;
    }

    public int getPrev_mon_spending() {
        return prev_mon_spending;
    }

    public int getCurrent_mon_spending() {
        return current_mon_spending;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }
}
