package tech.stl.hcm.candidate.service;

import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;
import java.util.List;
import java.util.UUID;

public interface CandidateWorkHistoryService {
    CandidateWorkHistoryDTO createCandidateWorkHistory(CandidateWorkHistoryDTO candidateWorkHistoryDTO);
    void deleteCandidateWorkHistory(Integer workHistoryId);
    CandidateWorkHistoryDTO retrieveCandidateWorkHistory(Integer workHistoryId);
    List<CandidateWorkHistoryDTO> retrieveCandidateWorkHistoriesByCandidateId(UUID candidateId);
    CandidateWorkHistoryDTO updateCandidateWorkHistory(CandidateWorkHistoryDTO candidateWorkHistoryDTO);
} 