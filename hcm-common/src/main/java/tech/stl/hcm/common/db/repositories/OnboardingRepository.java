package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.Offer;
import tech.stl.hcm.common.db.entities.Onboarding;

import java.util.List;
import java.util.Optional;

public interface OnboardingRepository extends JpaRepository<Onboarding, Integer> {
    Optional<Onboarding> findByOffer(Offer offer);
    List<Onboarding> findByCandidate(Candidate candidate);
} 