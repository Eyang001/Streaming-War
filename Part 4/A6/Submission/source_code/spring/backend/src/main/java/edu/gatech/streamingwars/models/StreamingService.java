package edu.gatech.streamingwars.models;

import javax.persistence.*;

@Entity
public class StreamingService {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String shortName;
    private String longName;
    private int subscriptionPrice;
    private int ltd_rev;
    private int prev_mon_rev;
    private int current_mon_rev;
    private int ltd_license_fee;



    public StreamingService(String shortName, String longName, int subscriptionPrice) {
        this.shortName = shortName;
        this.longName = longName;
        this.subscriptionPrice = subscriptionPrice;
    }

    public StreamingService(){};

    public void collectFee(int percentage, Event event, int offerPrice, DemographicGroup demographicGroup){

        // Event event = (offer.getEventId());//
        if(event.getType().equals("movie")) {
            int chargeMovie = getSubscriptionPrice() * demographicGroup.getNumber_of_accounts() * percentage / 100;
            ltd_rev += chargeMovie;
            current_mon_rev += chargeMovie;
        } else if (event.getType().equals("ppv")){
            int chargePPV = offerPrice * demographicGroup.getNumber_of_accounts() * percentage / 100;
            ltd_rev += chargePPV;
            current_mon_rev += chargePPV;
        }
    }

    public void payLicenseFee(Event event){
        ltd_license_fee += event.getLicensingFee();
    }

    public void restart(){
        prev_mon_rev = current_mon_rev;
        current_mon_rev = 0;
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

    public void setSubscriptionPrice(int subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
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

}

