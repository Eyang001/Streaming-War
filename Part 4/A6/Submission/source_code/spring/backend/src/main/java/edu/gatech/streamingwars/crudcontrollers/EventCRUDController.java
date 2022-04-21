package edu.gatech.streamingwars.crudcontrollers;

import edu.gatech.streamingwars.crudservices.EventCRUDService;
import edu.gatech.streamingwars.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/event")
@CrossOrigin(origins = "http://localhost:3001")
public class EventCRUDController {

    @Autowired
    private EventCRUDService eventCRUDService;

    // Event
    @PostMapping
    Event createEvent(@RequestBody Event event) {
        return eventCRUDService.saveEvent(event);
    }

    @GetMapping
    List<Event> allEvents() {
        return eventCRUDService.getEvents();
    }

    @GetMapping("/{id}")
    Event getEvent(@PathVariable Long id) {
        return eventCRUDService.getEvent(id);
    }

    //[14] update_event,test
    @PutMapping
    Event saveEvent(@RequestBody Event event) {
        //if not exists or if is watched so far -> can not update, throw away request
        Event found = eventCRUDService.findByNameAndYearProduced(event.getName(), event.getYearProduced());
        if (found == null || found.isOffered() || found.isWatched()) {
            return null; // TODO: return error message, how to do it?
        }
        long id = found.getId();
        return eventCRUDService.updateEvent(id, event);
    }

    //[14] update_event,test,test,stest,test,test
    @PutMapping("/{id}")
    Event saveEvent(@PathVariable Long id, @RequestBody Event event) {
        //if not exists or if is watched so far -> can not update, throw away request
        Event found = eventCRUDService.findByNameAndYearProduced(event.getName(), event.getYearProduced());
        if (found == null || found.isWatched()) {
            return null; // TODO: return error message, how to do it?
        }
        return eventCRUDService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    void deleteEvent(@PathVariable Long id) {
        eventCRUDService.deleteEvent(id);
    }
}
