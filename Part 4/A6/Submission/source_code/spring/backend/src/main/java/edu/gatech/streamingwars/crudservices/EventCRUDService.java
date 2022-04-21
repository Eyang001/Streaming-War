package edu.gatech.streamingwars.crudservices;

import edu.gatech.streamingwars.repositories.EventRepository;
import edu.gatech.streamingwars.repositories.StudioRepository;
import edu.gatech.streamingwars.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCRUDService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StudioRepository studioRepository;

    // Event CRUD
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event findByNameAndYearProduced(String name, String yearProduced) {
        return eventRepository.findByNameAndYearProduced(name, yearProduced);
    }

    // save
    public Event saveEvent(Event event) {
        Event found = eventRepository.findByNameAndYearProduced(event.getName(), event.getYearProduced());
        if (found != null) {
            return found;
        }
        event.setProduceId(studioRepository.findByShortName(event.getStudioShortName()).getId());
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event found = eventRepository.findById(id).orElse(null);
        if (found == null) {
            event.setId(id);
            return eventRepository.save(event);
        }
        //update event attributes
        found.setType(event.getType());
        found.setName(event.getName());
        found.setYearProduced(event.getYearProduced());
        found.setDuration(event.getDuration());
        found.setProduceId(event.getProduceId());
        found.setStudioShortName(event.getStudioShortName());
        found.setLicensingFee(event.getLicensingFee());
        found.setOffered(event.isOffered());
        found.setWatched(event.isWatched());
        eventRepository.save(found);
        return found;
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
