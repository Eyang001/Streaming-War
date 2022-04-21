package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    @Query("select s from Studio s where s.shortName = ?1")
    Studio findByShortName(String shortName);
}
