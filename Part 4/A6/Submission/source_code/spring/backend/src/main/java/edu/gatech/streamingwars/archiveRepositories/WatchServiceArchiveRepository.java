package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.WatchRecordArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchServiceArchiveRepository extends JpaRepository<WatchRecordArchive, Long> {
}
