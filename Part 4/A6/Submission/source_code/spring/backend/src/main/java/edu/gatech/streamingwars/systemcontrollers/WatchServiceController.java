package edu.gatech.streamingwars.systemcontrollers;

import edu.gatech.streamingwars.models.WatchRecord;
import edu.gatech.streamingwars.systemservices.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/watch")
@CrossOrigin(origins = "http://localhost:3001")
public class WatchServiceController {

    @Autowired
    private WatchService watchService;

    @PostMapping
    WatchRecord watchEvent(@RequestBody WatchRecord watchRecord) {
        //TODO : add exception handling ??
        return watchService.watchEvent(watchRecord);
    }

    @GetMapping
    List<WatchRecord> allWatchRecords() {
        return watchService.getWatchRecords();
    }

    @GetMapping("/{id}")
    WatchRecord getWatchRecord(@PathVariable Long id) {
        return watchService.getWatchRecord(id);
    }

    @PutMapping("/{id}")
    WatchRecord saveWatchRecord(@PathVariable Long id, @RequestBody WatchRecord watchRecord) {
        return watchService.updateWatchRecord(id, watchRecord);
    }

    @DeleteMapping("/{id}")
    void deleteWatchRecord(@PathVariable Long id) {
        watchService.deleteWatchRecord(id);
    }

}