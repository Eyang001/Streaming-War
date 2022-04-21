package edu.gatech.streamingwars.archiveRepositories;

import edu.gatech.streamingwars.archivemodels.OfferArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferArchiveRepository extends JpaRepository<OfferArchive, Long> {
}
