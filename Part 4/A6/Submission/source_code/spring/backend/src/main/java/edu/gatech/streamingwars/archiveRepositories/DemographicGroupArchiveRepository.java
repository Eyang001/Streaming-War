package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.DemographicGroupArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemographicGroupArchiveRepository extends JpaRepository<DemographicGroupArchive, Long> {

}
