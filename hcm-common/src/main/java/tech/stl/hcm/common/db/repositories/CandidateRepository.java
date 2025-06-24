package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Candidate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByEmail(String email);
    List<Candidate> findByOrganizationId(UUID organizationId);
} 