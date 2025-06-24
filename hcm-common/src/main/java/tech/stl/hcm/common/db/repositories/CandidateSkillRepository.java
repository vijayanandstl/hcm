package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.CandidateSkill;
import tech.stl.hcm.common.db.entities.CandidateSkillId;
import java.util.List;
import java.util.UUID;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    List<CandidateSkill> findByCandidateId(UUID candidateId);
}