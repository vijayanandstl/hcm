package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stl.hcm.common.db.entities.CandidateWorkHistory;
import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateWorkHistoryRepository extends JpaRepository<CandidateWorkHistory, Integer> {
    List<CandidateWorkHistory> findByCandidateId(UUID candidateId);
}