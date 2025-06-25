package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.CandidateCertification;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateCertificationRepository extends JpaRepository<CandidateCertification, Integer> {
    List<CandidateCertification> findByCandidateId(UUID candidateId);
    Optional<CandidateCertification> findByCertificationId(Integer certificationId);
    void deleteByCertificationId(Integer certificationId);
}   