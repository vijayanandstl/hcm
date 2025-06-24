package tech.stl.hcm.candidate.service;

import tech.stl.hcm.common.dto.CandidateEducationDTO;
import java.util.List;
import java.util.UUID;

public interface CandidateEducationService {
    CandidateEducationDTO createCandidateEducation(CandidateEducationDTO candidateEducationDTO);
    void deleteCandidateEducation(Integer educationId);
    CandidateEducationDTO retrieveCandidateEducation(Integer educationId);
    List<CandidateEducationDTO> retrieveCandidateEducationsByCandidateId(UUID candidateId);
    CandidateEducationDTO updateCandidateEducation(CandidateEducationDTO candidateEducationDTO);
} 