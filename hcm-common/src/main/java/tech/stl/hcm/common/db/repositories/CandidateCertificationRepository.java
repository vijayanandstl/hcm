package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stl.hcm.common.db.entities.CandidateCertification;
import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateCertificationRepository extends JpaRepository<CandidateCertification, Integer> {
    List<CandidateCertification> findByCandidate_CandidateId(UUID candidateId);
} 