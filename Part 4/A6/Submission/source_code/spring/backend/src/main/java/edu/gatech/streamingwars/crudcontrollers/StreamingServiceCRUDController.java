package edu.gatech.streamingwars.crudcontrollers;

import edu.gatech.streamingwars.crudservices.OfferCRUDService;
import edu.gatech.streamingwars.crudservices.StreamingServiceCRUDService;
import edu.gatech.streamingwars.models.StreamingService;
import edu.gatech.streamingwars.models.WatchRecord;
import edu.gatech.streamingwars.repositories.WatchServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/streamingservice")
@CrossOrigin(origins = "http://localhost:3001")
public class StreamingServiceCRUDController {

    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;

    @Autowired
    private OfferCRUDService offerCRUDService;

    @Autowired
    private WatchServiceRepository watchServiceRepository;

    // StreamingService
    @PostMapping
    StreamingService createStreamingService(@RequestBody StreamingService streamingService) {
        return streamingServiceCRUDService.saveStreamingService(streamingService);
    }

    @GetMapping
    List<StreamingService> allStreamingService() {
        return streamingServiceCRUDService.getStreamingServices();
    }

    @GetMapping("/single")
    StreamingService getStreamingServiceByShortName(@RequestParam String shortName) {
        StreamingService streamingService = streamingServiceCRUDService.findByShortName(shortName);
        return streamingServiceCRUDService.getStreamingService(streamingService.getId());
    }

    @GetMapping("/{id}")
    StreamingService getStreamingService(@PathVariable Long id) {
        return streamingServiceCRUDService.getStreamingService(id);
    }

    //[15] update_stream
    @PutMapping
    StreamingService saveStreamingService(@RequestBody StreamingService streamingService) {
        StreamingService found = streamingServiceCRUDService.findByShortName(streamingService.getShortName());
        if (found == null) { // if streaming service not exist, invalid
            return null; // TODO: return error message, how to do it?
        }
        // // retrieve from offer records if exists (accessed, this streamingservice is offering some event) cant't update
        // List<Offer> offers = offerCRUDService.findByStreamingServiceShortName(streamingService.getShortName());
        // if (offers != null && offers.size() != 0) {// this streamingservice is offering some events
        //     return null; // TODO: return error message, how to do it?
        // }
        // retrieve from watchedRecords if exists (accessed, some demogroup used this streamingservice to watched some event) can't update
        List<WatchRecord> watchRecords = watchServiceRepository.findByStreamingServiceShortName(streamingService.getShortName());
        if (watchRecords != null && watchRecords.size() != 0) { // has watching record so far, can't update subscription fee
            return null; // TODO: return error message, how to do it?
        }
        long id = found.getId();
        return streamingServiceCRUDService.updateStreamingService(id, streamingService);
    }

    //[15] update_stream
    @PutMapping("/{id}")
    StreamingService saveStreamingService(@PathVariable Long id, @RequestBody StreamingService streamingService) {
        StreamingService found = streamingServiceCRUDService.findByShortName(streamingService.getShortName());
        if (found == null) { // if streaming service not exist, invalid
            return null; // TODO: return error message, how to do it?
        }
        // // retrieve from offer records if exists (accessed, this streamingservice is offering some event) cant't update
        // List<Offer> offers = offerCRUDService.findByStreamingServiceShortName(streamingService.getShortName());
        // if (offers != null && offers.size() != 0) {// this streamingservice is offering some events
        //     return null; // TODO: return error message, how to do it?
        // }
        // retrieve from watchedRecords if exists (accessed, some demogroup used this streamingservice to watched some event) can't update
        List<WatchRecord> watchRecords = watchServiceRepository.findByStreamingServiceShortName(streamingService.getShortName());
        if (watchRecords != null && watchRecords.size() != 0) { // has watching record so far, can't update subscription fee
            return null; // TODO: return error message, how to do it?
        }
        return streamingServiceCRUDService.updateStreamingService(id, streamingService);
    }

    @DeleteMapping("/{id}")
    void deleteStreamingService(@PathVariable Long id) {
        streamingServiceCRUDService.deleteStreamingService(id);
    }

}
