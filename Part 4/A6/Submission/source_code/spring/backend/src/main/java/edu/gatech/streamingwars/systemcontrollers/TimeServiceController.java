package edu.gatech.streamingwars.systemcontrollers;

import edu.gatech.streamingwars.archivemodels.ArchiveData;
import edu.gatech.streamingwars.models.TimeData;
import edu.gatech.streamingwars.streamingwarsystem.StreamingWarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/nextmonth")
@CrossOrigin(origins = "http://localhost:3001")
public class TimeServiceController {

    @Autowired
    private StreamingWarSystem streamingWarSystem;

    @PostMapping
    TimeData moveNext(@RequestBody TimeData td) {
        streamingWarSystem.setYear(2020);
        streamingWarSystem.setMonth(10);
        return td;
    }

    @GetMapping("/display")
    List<Integer> displayTime() {
        return streamingWarSystem.displayTime();
    }

    @GetMapping("/next")
    ArchiveData next() {
        return streamingWarSystem.nextMonth();
    }

    // @RequestMapping("/service/nextmonth")
    // public ModelAndView show() {
    //     String msg = "nextmonth";
    //     return new ModelAndView("nextmonth", "message", msg);
    // }

    // public String showForm() {
    //     return "Click to move to next month.";
    // }

    // @RequestMapping(value="/movetonextmonth", params="submit", method= RequestMethod.POST)
    // public void submit() {
    //     streamingWarSystem.nextMonth();
    // }

    // @RequestMapping(value="/movetonextmonth", params="cancel", method= RequestMethod.POST)
    // public void cancel() {
    //     streamingWarSystem.resetMonth();
    // }

}
