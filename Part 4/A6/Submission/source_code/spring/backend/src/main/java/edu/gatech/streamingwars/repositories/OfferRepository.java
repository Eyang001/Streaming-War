package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o from Offer o where o.streamingServiceName = ?1")
    List<Offer> findByStreamingServiceShortName(String streamingServiceShortName);

    @Query("select o from Offer o where o.streamingServiceId = ?1 and o.eventId = ?2")
    Offer findByStreamingServiceIdAndEventId(long streamingServiceId, long eventId);

    @Query("select o from Offer o where o.streamingServiceName = ?1 and o.eventName = ?2 and o.yearProduced = ?3")
    Offer findByStreamingServiceNameAndEventNameAndYearProduced(String streamingServiceName, String eventName, String yearProduced);
}
