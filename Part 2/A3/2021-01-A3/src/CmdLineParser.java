import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;

public class CmdLineParser {

    private static StreamingWarSystem streaming_war;

    public CmdLineParser() {
        streaming_war = new StreamingWarSystem();
    }

    public void processInstructions(Boolean verboseMode) {
        int selectDemo, selectEvent, selectStudio, selectStream, selectOffer;
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("create_demo")) {
                    if (verboseMode) { System.out.println("create_demo_acknowledged"); }
                    streaming_war.create_demo(tokens[1], tokens[2], Integer.parseInt(tokens[3]));

                } else if (tokens[0].equals("create_studio")) {
                    if (verboseMode) { System.out.println("create_studio_acknowledged"); }
                    streaming_war.create_studio(tokens[1], tokens[2]);

                } else if (tokens[0].equals("create_event")) {
                    if (verboseMode) { System.out.println("create_event_acknowledged"); }
                    streaming_war.create_event(tokens[1],tokens[2],tokens[3],
                            Integer.parseInt(tokens[4]),tokens[5],Integer.parseInt(tokens[6]));

                } else if (tokens[0].equals("create_stream")) {
                    if (verboseMode) { System.out.println("create_stream_acknowledged"); }
                    streaming_war.create_stream(tokens[1], tokens[2], Integer.parseInt(tokens[3]));

                } else if (tokens[0].equals("offer_movie") || tokens[0].equals("offer_ppv")) {
                    if (verboseMode) { System.out.println(tokens[0] + "_acknowledged"); }
                    if(tokens[0].equals("offer_movie")){
                        streaming_war.offer_movie(tokens[1], tokens[2],tokens[3]);
                    } else if(tokens[0].equals("offer_ppv")){
                        streaming_war.offer_ppv(tokens[1], tokens[2],tokens[3],Integer.parseInt(tokens[4]));
                    }

                } else if (tokens[0].equals("watch_event")) {
                    if (verboseMode) { System.out.println("watch_event_acknowledged"); }
                    streaming_war.watch_event(tokens[1], Integer.parseInt(tokens[2]),tokens[3], tokens[4], tokens[5]);

                } else if (tokens[0].equals("next_month")) {
                    if (verboseMode) { System.out.println("next_month_acknowledged"); }
                    streaming_war.next_month();

                } else if (tokens[0].equals("display_demo")) {
                    if (verboseMode) { System.out.println("display_demo_acknowledged"); }
                    streaming_war.display_demo(tokens[1]);

                } else if (tokens[0].equals("display_events")) {
                    if (verboseMode) { System.out.println("display_events_acknowledged"); }
                    streaming_war.display_events();

                } else if (tokens[0].equals("display_stream")) {
                    if (verboseMode) { System.out.println("display_stream_acknowledged"); }
                    streaming_war.display_stream(tokens[1]);

                } else if (tokens[0].equals("display_studio")) {
                    if (verboseMode) { System.out.println("display_studio_acknowledged"); }
                    streaming_war.display_studio(tokens[1]);

                } else if (tokens[0].equals("display_offers")) {
                    if (verboseMode) { System.out.println("display_offers_acknowledged"); }
                    streaming_war.display_offers();

                } else if (tokens[0].equals("display_time")) {
                    if (verboseMode) { System.out.println("display_time_acknowledged"); }
                    streaming_war.display_time();
                } else if (tokens[0].equals("stop")) {
                    break;
                } else {
                    if (verboseMode) { System.out.println("command_" + tokens[0] + "_NOT_acknowledged"); }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        if (verboseMode) { System.out.println("stop_acknowledged"); }
        commandLineInput.close();
    }

}
