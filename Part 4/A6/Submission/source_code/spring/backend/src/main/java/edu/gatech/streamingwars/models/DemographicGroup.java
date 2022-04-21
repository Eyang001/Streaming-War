package edu.gatech.streamingwars.models;

import javax.persistence.*;

@Entity
public class DemographicGroup {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String shortName;
    private String longName;
    private int number_of_accounts;

    private int ltd_spending;
    private int prev_mon_spending;
    private int current_mon_spending;



    public DemographicGroup(String shortName, String longName, int numberOfAccounts){
        this.shortName = shortName;
        this.longName = longName;
        this.number_of_accounts = numberOfAccounts;
    }

    public DemographicGroup(){}


    public void pay(StreamingService streamingService, int percentage){
        int charge = streamingService.getSubscriptionPrice() * number_of_accounts * percentage /100;
        ltd_spending += charge;
        current_mon_spending += charge;
    }

    public void pay(Integer price, int percentage){
        int charge = price * number_of_accounts * percentage / 100;
        ltd_spending += charge;
        current_mon_spending += charge;
    }

    public void restart(){
        // currentSubscription.clear();
        prev_mon_spending = current_mon_spending;
        current_mon_spending = 0;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) { this.longName = longName; }

    public void setNumber_of_accounts(int number_of_accounts) { this.number_of_accounts = number_of_accounts; }

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
}

