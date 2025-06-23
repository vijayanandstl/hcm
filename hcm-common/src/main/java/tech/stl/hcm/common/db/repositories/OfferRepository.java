package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Application;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByApplication(Application application);
    List<Offer> findByCandidate(Candidate candidate);
} 