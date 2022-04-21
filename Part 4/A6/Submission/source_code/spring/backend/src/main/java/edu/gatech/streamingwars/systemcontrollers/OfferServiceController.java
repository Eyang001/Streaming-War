package edu.gatech.streamingwars.systemcontrollers;

import edu.gatech.streamingwars.crudservices.EventCRUDService;
import edu.gatech.streamingwars.crudservices.StreamingServiceCRUDService;
import edu.gatech.streamingwars.models.Event;
import edu.gatech.streamingwars.models.Offer;
import edu.gatech.streamingwars.models.StreamingService;
import edu.gatech.streamingwars.systemservices.OfferService;
import edu.gatech.streamingwars.systemservices.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/offer")
@CrossOrigin(origins = "http://localhost:3001")
public class OfferServiceController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private StreamingServiceCRUDService streamingServiceCRUDService;

    @Autowired
    private EventCRUDService eventCRUDService;

    @Autowired
    private WatchService watchService;


    @PostMapping
    Offer createOffer(@RequestBody Offer offer) {
        //TODO : add exception handling ??
        return offerService.saveOffer(offer);
    }

    // @ExceptionHandler(NoSuchElementException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // public ResponseEntity<String> handleNoSuchElementFoundException(
    //         NoSuchElementException exception
    // ) {
    //     return ResponseEntity
    //             .status(HttpStatus.NOT_FOUND)
    //             .body(exception.getMessage());
    // }

    @GetMapping
    List<Offer> allOffers() {
        return offerService.getOffers();
    }

    @GetMapping("/{id}")
    Offer getOffer(@PathVariable Long id) {
        return offerService.getOffer(id);
    }

    @PutMapping("/{id}")
    Offer saveOffer(@PathVariable Long id, @RequestBody Offer offer) {
        return offerService.updateOffer(id, offer);
    }

    @DeleteMapping("/{id}")
    void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
    }

    // [16] retract_movie
    @DeleteMapping
    void deleteEventFromOffer(@RequestParam String streamingServiceShortName,
                              @RequestParam String eventName,
                              @RequestParam String yearProduced) {
        //check if streaming service exists
        StreamingService streamingService = streamingServiceCRUDService.findByShortName(streamingServiceShortName);
        if (streamingService == null) return;
        //check if event exists, and if is movie
        Event event = eventCRUDService.findByNameAndYearProduced(eventName, yearProduced);
        if (event == null || !event.getType().equals("movie") ) return;
        //check if event is being offered or being watched
        if (event.isOffered() || event.isWatched()) return;

        //check if offer record exists
        Offer found = offerService.findByStreamingServiceNameAndEventNameAndYearProduced(
                streamingServiceShortName,
                eventName,
                yearProduced
                );
        if (found == null) return;

        // change from isOffer to query from db
        // event.setOffered(false);

        offerService.deleteOffer(found.getId());
    }

}
