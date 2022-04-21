
import java.util.ArrayList;

public class StreamingWarSystem {
    private ArrayList<DemographicGroup> demo;
    private ArrayList<StreamingService> stream;
    private ArrayList<Event> event;
    private ArrayList<Studio> studio;
    private int month;
    private int year;

    public StreamingWarSystem(){
        demo = new ArrayList<DemographicGroup>();
        stream = new ArrayList<StreamingService>();
        event = new ArrayList<Event>();
        studio = new ArrayList<Studio>();
        month = 10;
        year = 2020;
    }

    //[1]
    public void create_demo(String shortName, String longName, Integer number_of_accounts){
        int demoId = validateDemo(shortName);
        if(demoId != -1){ return; }
        demo.add(new DemographicGroup(shortName, longName, number_of_accounts));
    }

    //[2]
    public void create_studio(String shortName, String longName){
        int studioId = validateStudio(shortName);
        if(studioId != -1){ return; }
        studio.add(new Studio(shortName, longName));
    }

    //[3]
    public void create_event(String type, String name, String yearProduced, int duration, String studio_name, int license_fee){
        //check if event already exists
        int eventId = validateEvent(name,yearProduced);
        if(eventId != -1){ return; }

        int studioId = validateStudio(studio_name);
        if(studioId == -1){ return; }
        Studio curr_studio = studio.get(studioId);


        if(type.equals("movie")){
            Movie curr_movie = new Movie(type, name, yearProduced, duration, curr_studio, license_fee);
            event.add(curr_movie);
            curr_studio.produce(curr_movie);
        } else if (type.equals("ppv")){
            PPV curr_ppv = new PPV(type,name, yearProduced, duration, curr_studio, license_fee);
            event.add(curr_ppv);
            curr_studio.produce(curr_ppv);
        }
    };

    //[4]
    public void create_stream(String short_name, String long_name, int subscription_fee){
        int streamId = validateStream(short_name);
        if(streamId != -1) { return; }
        stream.add(new StreamingService(short_name, long_name, subscription_fee));
    }

    //[5]
    public void offer_movie(String stream_name, String movie_name, String year_produced){
        //validate event, streamingService and studio
        Movie movie_offered = new Movie();
        StreamingService stream_offering = new StreamingService();
        Studio studio_produced = new Studio();

        int eventId = validateEvent(movie_name, year_produced);
        int studioId = -1;
        if(eventId != -1){
            movie_offered = (Movie) event.get(eventId);
            studioId = validateStudio(movie_offered.getProducedBy().getShortName());
            studio_produced = studio.get(studioId);
        }
        int streamId = validateStream(stream_name);

        if(eventId ==-1 || streamId == -1 || studioId == -1){ return; }
        stream_offering = stream.get(streamId);
        stream_offering.offerEvent(movie_offered);
        studio_produced.collectLicenseFee(movie_offered);

    }

    //[6]
    public void offer_ppv(String stream_name, String ppv_name, String year_produced, int price){
        //validate event, streamingService and studio
        PPV ppv_offered = new PPV();
        StreamingService stream_offering = new StreamingService();
        Studio studio_produced = new Studio();

        int eventId = validateEvent(ppv_name, year_produced);
        int studioId = -1;
        if(eventId != -1){
            ppv_offered = (PPV) event.get(eventId);
            ppv_offered.setPrice(price);
            studioId = validateStudio(ppv_offered.getProducedBy().getShortName());
            studio_produced = studio.get(studioId);
        }
        int streamId = validateStream(stream_name);

        if(eventId ==-1 || streamId == -1 || studioId == -1){ return; }
        stream_offering = stream.get(streamId);
        stream_offering.offerEvent(ppv_offered);
        studio_produced.collectLicenseFee(ppv_offered);
    }

    //[7]
    public void watch_event(String demo_group, int percentage, String stream_service, String event_name, String yearProduced){
        //validate demo_group, streaming_service and event
        int demoId = validateDemo(demo_group);
        int streamId = validateStream(stream_service);
        int eventId = validateEvent(event_name, yearProduced);

        if(eventId ==-1 || streamId == -1 || demoId == -1){ return; }


        //validate offering at stream_service
        StreamingService curr_stream = stream.get(streamId);
        Event curr_event = event.get(eventId);
        if(!curr_stream.getCurrentOffering().contains(curr_event)) return;

        DemographicGroup curr_demo = demo.get(demoId);
        curr_demo.watch(percentage,curr_stream, curr_event);
        //curr_stream.collectFee(percentage, curr_event, curr_demo);
    }

    //[8]
    public void next_month() {
        if (month == 12) { month = 1; year++; } else { month++; }

        for(DemographicGroup d: demo){ d.restart(); }
        for(StreamingService s: stream) { s.restart();}
        for(Studio s: studio) { s.restart();}
    }

    //[9]
    public void display_demo(String shortName){
        int demoId = validateDemo(shortName);
        if(demoId == -1) return;

        DemographicGroup curr_demo = demo.get(demoId);
        System.out.println("demo," + curr_demo.getShortName() + ","+ curr_demo.getLongName());
        System.out.println("size,"+curr_demo.getNumber_of_accounts());
        System.out.println("current_period,"+curr_demo.getCurrent_mon_spending());
        System.out.println("previous_period,"+curr_demo.getPrev_mon_spending());
        System.out.println("total,"+(curr_demo.getLtd_spending()-curr_demo.getCurrent_mon_spending()));
    }

    //[10]
    public void display_stream(String shortName){
        int streamId = validateStream(shortName);
        if(streamId == -1) return;

        StreamingService curr_stream = stream.get(streamId);
        System.out.println("stream," + curr_stream.getShortName() + ","+ curr_stream.getLongName());
        System.out.println("subscription,"+curr_stream.getSubscriptionPrice());
        System.out.println("current_period,"+curr_stream.getCurrent_mon_rev());
        System.out.println("previous_period,"+curr_stream.getPrev_mon_rev());
        System.out.println("total,"+(curr_stream.getLtd_rev()-curr_stream.getCurrent_mon_rev()));
        System.out.println("licensing,"+curr_stream.getLtd_license_fee());
    }

    //[11]
    public void display_studio(String shortName){
        int studioId = validateStudio(shortName);
        if(studioId == -1) return;

        Studio curr_studio = studio.get(studioId);
        System.out.println("studio," + curr_studio.getShortName() + ","+ curr_studio.getLongName());
        System.out.println("current_period,"+curr_studio.getCurrent_mon_fee_collected());
        System.out.println("previous_period,"+curr_studio.getPrev_mon_fee_collected());
        System.out.println("total,"+(curr_studio.getLtd_fee_collected()-curr_studio.getCurrent_mon_fee_collected()));
    }

    //[12]
    public void display_events(){
        for(Event e: event ){
            System.out.println(e.getType() + "," + e.getName() + "," + e.getYearProduced() + "," + e.getDuration() + ","
            + e.getProducedBy().getShortName() + "," + e.getLicensingFee());
        }
    }

    //[13]
    public void display_offers(){
        for(StreamingService s: stream){
            for(Event e: s.getCurrentOffering()){
                if(e.getType().equals("movie")){
                    System.out.println(s.getShortName() + "," + e.getType() + "," + e.getName() + "," + e.getYearProduced());
                } else if (e.getType().equals("ppv")){
                    System.out.println(s.getShortName() + "," + e.getType() + "," + e.getName() + "," + e.getYearProduced()
                    + "," + ((PPV)e).getPrice());
                }
            }
        }
    }

    //[14]
    public void display_time(){
        System.out.println("time," + month + "," + year);
    }

    //Validators
    public int validateDemo(String shortName){
        for(DemographicGroup d: demo){
            if(d.getShortName().equals(shortName)){
                return demo.indexOf(d);
            }
        }
        return -1;
    }

    public int validateStudio(String shortName){
        for(Studio s: studio){
            if(s.getShortName().equals(shortName)){
                return studio.indexOf(s);
            }
        }
        return -1;
    }

    public int validateEvent(String shortName, String yearProduced){
        for(Event e: event){
            String id = e.getName() + e.getYearProduced();
            if(id.equals(shortName+yearProduced)){
                return event.indexOf(e);
            }
        }
        return -1;
    }

    public int validateStream(String shortName){
        for(StreamingService s: stream){
            if(s.getShortName().equals(shortName)){
                return stream.indexOf(s);
            }
        }
        return -1;
    }

}
