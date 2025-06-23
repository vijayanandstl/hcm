package tech.stl.hcm.candidate.service;

import tech.stl.hcm.common.db.entities.CandidateSkillId;
import tech.stl.hcm.common.dto.CandidateSkillDTO;
import java.util.List;
import java.util.UUID;

public interface CandidateSkillService {
    CandidateSkillDTO createCandidateSkill(CandidateSkillDTO candidateSkillDTO);
    void deleteCandidateSkill(CandidateSkillId candidateSkillId);
    CandidateSkillDTO retrieveCandidateSkill(CandidateSkillId candidateSkillId);
    List<CandidateSkillDTO> retrieveCandidateSkillsByCandidateId(UUID candidateId);
    CandidateSkillDTO updateCandidateSkill(CandidateSkillDTO candidateSkillDTO);
} 