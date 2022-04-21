package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.DemographicGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DemographicGroupRepository extends JpaRepository<DemographicGroup, Long> {

    @Query("select d from DemographicGroup d where d.shortName = ?1")
    DemographicGroup findByShortName(String shortName);
}
