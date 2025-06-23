package tech.stl.hcm.candidate.service;

import tech.stl.hcm.common.dto.CandidateDTO;

import java.util.List;
import java.util.UUID;

public interface CandidateService {
    CandidateDTO createCandidate(CandidateDTO candidateDTO);
    void deleteCandidate(UUID candidateId);
    CandidateDTO retrieveCandidate(UUID candidateId);
    CandidateDTO updateCandidate(UUID candidateId, CandidateDTO candidateDTO);
    List<CandidateDTO> getAllCandidates();
} 