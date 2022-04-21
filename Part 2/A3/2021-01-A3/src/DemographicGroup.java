import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

public class DemographicGroup {
    private String shortName;
    private String longName;
    private int number_of_accounts;
    public HashMap<StreamingService, Integer> currentSubscription;
    private int ltd_spending;
    private int prev_mon_spending;
    private int current_mon_spending;

    public DemographicGroup(String shortName, String longName, int numberOfAccounts){
        this.shortName = shortName;
        this.longName = longName;
        this.number_of_accounts = numberOfAccounts;
        this.currentSubscription = new HashMap<StreamingService, Integer>();
    }

    public void watch(int percentage, StreamingService streamingService, Event event){
        if(event.getType().equals("movie")){
            if(getCurrentSubscription().containsKey(streamingService)){
                if(getCurrentSubscription().get(streamingService) >= percentage) return;
                else subscribe(streamingService, percentage, event);
            }
            else subscribe(streamingService, percentage, event);
        } else if (event.getType().equals("ppv")){
            pay((PPV) event, percentage);
            streamingService.collectFee(percentage,event,this);
        }
    }

    public void subscribe(StreamingService streamingService, int percentage, Event event){
        int current_percent;
            if(getCurrentSubscription().get(streamingService) == null)  {
                current_percent = 0;
            } else {
                current_percent = getCurrentSubscription().get(streamingService);
            }
        this.currentSubscription.put(streamingService, percentage);
        pay(streamingService, percentage - current_percent);
        streamingService.collectFee(percentage - current_percent,event, this);
    }


    public void pay(StreamingService streamingService, int percentage){
        int charge = streamingService.getSubscriptionPrice() * number_of_accounts * percentage /100;
        ltd_spending += charge;
        current_mon_spending += charge;
    }

    public void pay(PPV ppv, int percentage){
        int charge = ppv.getPrice() * number_of_accounts * percentage / 100;
        ltd_spending += charge;
        current_mon_spending += charge;
    }

    public void restart(){
        currentSubscription.clear();
        prev_mon_spending = current_mon_spending;
        current_mon_spending = 0;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }


    public HashMap<StreamingService, Integer> getCurrentSubscription() {
        return currentSubscription;
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
}
