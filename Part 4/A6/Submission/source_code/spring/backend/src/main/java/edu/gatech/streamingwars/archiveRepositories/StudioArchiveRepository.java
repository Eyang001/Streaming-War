package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.StudioArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioArchiveRepository extends JpaRepository<StudioArchive, Long> {
}
