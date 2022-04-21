package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.ArchiveData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends JpaRepository<ArchiveData, Long> {

    @Query("select a from ArchiveData a where a.timestamp = ?1")
    ArchiveData findByTimestamp(String timestamp);

    @Query("select a.id from ArchiveData a where a.timestamp = ?1")
    Long findIdByTimestamp(String timestamp);

}
