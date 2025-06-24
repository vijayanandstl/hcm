package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stl.hcm.common.db.entities.CandidateEducation;
import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Integer> {
    List<CandidateEducation> findByCandidateId(UUID candidateId);
} 