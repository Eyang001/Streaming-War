package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.StreamingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingServiceRepository extends JpaRepository<StreamingService, Long> {

    @Query("select s from StreamingService s where s.shortName = ?1")
    StreamingService findByShortName(String shortName);
}
