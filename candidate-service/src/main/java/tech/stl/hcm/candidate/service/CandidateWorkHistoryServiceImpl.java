package tech.stl.hcm.candidate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.CandidateWorkHistory;
import tech.stl.hcm.common.db.repositories.CandidateWorkHistoryRepository;
import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateWorkHistoryServiceImpl implements CandidateWorkHistoryService {

    private final CandidateWorkHistoryRepository candidateWorkHistoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CandidateWorkHistoryDTO createCandidateWorkHistory(CandidateWorkHistoryDTO candidateWorkHistoryDTO) {
        CandidateWorkHistory candidateWorkHistory = modelMapper.map(candidateWorkHistoryDTO, CandidateWorkHistory.class);
        Candidate candidate = new Candidate();
        candidate.setCandidateId(candidateWorkHistoryDTO.getCandidateId());
        candidateWorkHistory.setCandidate(candidate);
        candidateWorkHistory = candidateWorkHistoryRepository.save(candidateWorkHistory);
        log.info("New Candidate Work History has been created for candidate: {}", candidateWorkHistory.getCandidate().getCandidateId());
        return modelMapper.map(candidateWorkHistory, CandidateWorkHistoryDTO.class);
    }

    @Override
    public void deleteCandidateWorkHistory(Integer workHistoryId) {
        candidateWorkHistoryRepository.deleteById(workHistoryId);
        log.info("Candidate Work History has been deleted: {}", workHistoryId);
    }

    @Override
    public CandidateWorkHistoryDTO retrieveCandidateWorkHistory(Integer workHistoryId) {
        log.info("Candidate Work History retrieval event for work history: {}", workHistoryId);
        return candidateWorkHistoryRepository.findById(workHistoryId)
                .map(workHistory -> modelMapper.map(workHistory, CandidateWorkHistoryDTO.class))
                .orElse(null);
    }

    @Override
    public List<CandidateWorkHistoryDTO> retrieveCandidateWorkHistoriesByCandidateId(UUID candidateId) {
        return candidateWorkHistoryRepository.findByCandidate_CandidateId(candidateId)
                .stream()
                .map(workHistory -> modelMapper.map(workHistory, CandidateWorkHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateWorkHistoryDTO updateCandidateWorkHistory(CandidateWorkHistoryDTO candidateWorkHistoryDTO) {
        CandidateWorkHistory workHistory = candidateWorkHistoryRepository.findById(candidateWorkHistoryDTO.getWorkHistoryId()).orElse(null);
        if (workHistory != null) {
            modelMapper.map(candidateWorkHistoryDTO, workHistory);
            workHistory = candidateWorkHistoryRepository.save(workHistory);
            log.info("Candidate Work History has been updated: {}", workHistory.getWorkHistoryId());
            return modelMapper.map(workHistory, CandidateWorkHistoryDTO.class);
        } else {
            log.info("Candidate Work History not found for update: {}", candidateWorkHistoryDTO.getWorkHistoryId());
        }
        return null;
    }
} 