import java.lang.reflect.Array;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StreamingService {
    private String shortName;
    private String longName;
    private int subscriptionPrice;
    private ArrayList<Event> currentOffering;
    private int ltd_rev;
    private int prev_mon_rev;
    private int current_mon_rev;
    private int ltd_license_fee;

    public StreamingService(String shortName, String longName, int subscriptionPrice) {
        this.shortName = shortName;
        this.longName = longName;
        this.subscriptionPrice = subscriptionPrice;
        this.currentOffering = new ArrayList<Event>();
    }

    public StreamingService(){};

    public void offerEvent(Event event){
        this.currentOffering.add(event);
        payLicenseFee(event);
    }

    public void collectFee(int percentage, Event event, DemographicGroup demographicGroup){
        if(event.getType().equals("movie")) {
            int chargeMovie = getSubscriptionPrice() * demographicGroup.getNumber_of_accounts() * percentage / 100;
            ltd_rev += chargeMovie;
            current_mon_rev += chargeMovie;
        } else if (event.getType().equals("ppv")){
            int chargePPV = ((PPV) event).getPrice() * demographicGroup.getNumber_of_accounts() * percentage / 100;
            ltd_rev += chargePPV;
            current_mon_rev += chargePPV;
        }
    }

    public void payLicenseFee(Event event){
        ltd_license_fee += event.getLicensingFee();
    }

    public void restart(){
        currentOffering.clear();
        prev_mon_rev = current_mon_rev;
        current_mon_rev = 0;
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

    public ArrayList<Event> getCurrentOffering() {
        return currentOffering;
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
