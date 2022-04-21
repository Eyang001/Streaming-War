package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.StreamingServiceArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingServiceArchiveRepository extends JpaRepository<StreamingServiceArchive, Long> {
}
