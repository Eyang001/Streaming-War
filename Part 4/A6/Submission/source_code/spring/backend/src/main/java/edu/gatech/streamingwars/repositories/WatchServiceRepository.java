package edu.gatech.streamingwars.repositories;

import edu.gatech.streamingwars.models.WatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchServiceRepository extends JpaRepository<WatchRecord, Long> {

    @Query("select w from WatchRecord w where w.demographicGroupShortName = ?1")
    List<WatchRecord> findByDemographicGroupShortName(String demographicGroupShortName);

    @Query("select w from WatchRecord w where w.streamingServiceShortName = ?1")
    List<WatchRecord> findByStreamingServiceShortName(String streamingServiceShortName);

    @Query("select w from WatchRecord w where w.streamingServiceId = ?1 and w.demographicGroupShortName = ?2")
    WatchRecord findByStreamingServiceIdAndDemographicGroupShortName(long streamingServiceId, String demographicGroupShortName);
}
