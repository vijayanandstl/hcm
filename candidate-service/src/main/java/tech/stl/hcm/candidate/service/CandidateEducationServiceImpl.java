package tech.stl.hcm.candidate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.CandidateEducation;
import tech.stl.hcm.common.db.repositories.CandidateEducationRepository;
import tech.stl.hcm.common.db.repositories.CandidateRepository;
import tech.stl.hcm.common.dto.CandidateEducationDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateEducationServiceImpl implements CandidateEducationService {

    private final CandidateEducationRepository candidateEducationRepository;
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Override
    public CandidateEducationDTO createCandidateEducation(UUID candidateId, CandidateEducationDTO candidateEducationDTO) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));
        CandidateEducation candidateEducation = modelMapper.map(candidateEducationDTO, CandidateEducation.class);
        candidateEducation.setCandidateId(candidate.getCandidateId());
        CandidateEducation savedEducation = candidateEducationRepository.save(candidateEducation);
        log.info("New Candidate Education has been created for candidate: {}", savedEducation.getCandidateId());
        return modelMapper.map(savedEducation, CandidateEducationDTO.class);
    }

    @Override
    public void deleteCandidateEducation(Integer educationId) {
        candidateEducationRepository.deleteById(educationId);
        log.info("Candidate Education has been deleted: {}", educationId);
    }

    @Override
    public CandidateEducationDTO retrieveCandidateEducation(Integer educationId) {
        log.info("Candidate Education retrieval event for education: {}", educationId);
        return candidateEducationRepository.findById(educationId)
                .map(education -> modelMapper.map(education, CandidateEducationDTO.class))
                .orElse(null);
    }

    @Override
    public List<CandidateEducationDTO> retrieveCandidateEducationsByCandidateId(UUID candidateId) {
        return candidateEducationRepository.findByCandidateId(candidateId)
                .stream()
                .map(education -> modelMapper.map(education, CandidateEducationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateEducationDTO updateCandidateEducation(CandidateEducationDTO candidateEducationDTO) {
        List<CandidateEducation> educations = candidateEducationRepository.findByCandidateId(candidateEducationDTO.getCandidateId());
        CandidateEducation education = educations.isEmpty() ? null : educations.get(0);
        if (education != null) {
            modelMapper.map(candidateEducationDTO, education);
            education = candidateEducationRepository.save(education);
            log.info("Candidate Education has been updated: {}", education.getEducationId());
            return modelMapper.map(education, CandidateEducationDTO.class);
        } else {
            log.info("Candidate Education not found for update: {}", candidateEducationDTO.getCandidateId());
        }
        return null;
    }
} 