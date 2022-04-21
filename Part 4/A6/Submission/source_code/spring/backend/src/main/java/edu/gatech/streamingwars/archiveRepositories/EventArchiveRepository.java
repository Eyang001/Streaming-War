package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.EventArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventArchiveRepository extends JpaRepository<EventArchive, Long> {
}
