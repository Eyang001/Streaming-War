package edu.gatech.streamingwars.models;

import javax.persistence.*;

@Entity
public class Studio {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String shortName;
    private String longName;
    // private List<Event> currentListing;
    private int ltd_fee_collected;
    private int prev_mon_fee_collected;
    private int current_mon_fee_collected;



    public Studio(String shortName, String longName){
        this.shortName = shortName;
        this.longName = longName;
        // this.currentListing = new ArrayList<Event>();
    }

    public Studio(){}

    public void collectLicenseFee(Event event){
        ltd_fee_collected += event.getLicensingFee();
        current_mon_fee_collected += event.getLicensingFee();
    }

    // public void produce(Movie movie){
    //     this.currentListing.add(movie);
    // }
    //
    // public void produce(PPV ppv){
    //     this.currentListing.add(ppv);
    // }


    public void restart(){
        prev_mon_fee_collected = getCurrent_mon_fee_collected();
        current_mon_fee_collected = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
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

}

