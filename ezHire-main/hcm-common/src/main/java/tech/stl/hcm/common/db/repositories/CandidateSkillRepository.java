package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.stl.hcm.common.db.entities.CandidateSkill;
import tech.stl.hcm.common.db.entities.CandidateSkillId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    List<CandidateSkill> findByCandidate_CandidateId(UUID candidateId);
    Optional<CandidateSkill> findByCandidate_CandidateIdAndSkill_SkillId(UUID candidateId, Integer skillId);
} 