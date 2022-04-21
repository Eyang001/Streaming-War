package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // @Query("select new edu.gatech.streamingwars.dto.EventDTO(e.type, e.name, e.yearProduced, e.duration, e.studioShortName, e.licensingFee, e.produceId) " +
    //         "from Event e " +
    //         "where e.name = ?1 and e.yearProduced = ?2")
    // List<EventDTO> findByNameAndYearProducedDTO(String name, String yearProduced);

    // EventView findViewByNameAndYearProduced(String name, String yearProduced);

    @Query("select e from Event e where e.name = ?1 and e.yearProduced = ?2")
    Event findByNameAndYearProduced(String name, String yearProduced);

    @Query("select e.produceId from Event e where e.id = ?1")
    Long findStudioIdByEventId(Long eventId);
}
