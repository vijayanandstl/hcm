package tech.stl.hcm.candidate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.stl.hcm.candidate.exception.CandidateNotFoundException;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.repositories.CandidateRepository;
import tech.stl.hcm.common.dto.CandidateDTO;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        if (Objects.nonNull(candidateRepository.findByEmail(candidateDTO.getEmail()))) {
            log.error("Candidate already exists with email: {}", candidateDTO.getEmail());
            throw new RuntimeException("Candidate already exists with email: " + candidateDTO.getEmail());
        }
        Candidate candidate = modelMapper.map(candidateDTO, Candidate.class);
        if (!Objects.isNull(candidate.getCandidateId())) {
            Candidate savedCandidate = candidateRepository.save(candidate);
            log.info("New Candidate has been created: {}", savedCandidate.getEmail());
            return modelMapper.map(savedCandidate, CandidateDTO.class);
        }
        throw new RuntimeException("Failed to create a Candidate with email: " + candidateDTO.getEmail());
    }

    @Override
    @Transactional
    public void deleteCandidate(UUID candidateId) {
        candidateRepository.deleteById(candidateId);
        log.info("Candidate has been deleted: {}", candidateId);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO retrieveCandidate(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + candidateId));
        return modelMapper.map(candidate, CandidateDTO.class);
    }

    @Override
    @Transactional
    public CandidateDTO updateCandidate(UUID candidateId, CandidateDTO candidateDTO) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found for update with id: " + candidateId));

        modelMapper.map(candidateDTO, candidate);
        Candidate updatedCandidate = candidateRepository.save(candidate);
        log.info("Candidate has been updated: {}", updatedCandidate.getEmail());
        return modelMapper.map(updatedCandidate, CandidateDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidateDTO> getAllCandidates() {
        return candidateRepository.findAll().stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDTO.class))
                .collect(Collectors.toList());
    }
} 