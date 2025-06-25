package tech.stl.hcm.candidate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.stl.hcm.candidate.exception.CandidateNotFoundException;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.CandidateCertification;
import tech.stl.hcm.common.db.repositories.CandidateCertificationRepository;
import tech.stl.hcm.common.db.repositories.CandidateRepository;
import tech.stl.hcm.common.dto.CandidateCertificationDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateCertificationServiceImpl implements CandidateCertificationService {

    private final CandidateCertificationRepository candidateCertificationRepository;
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createCandidateCertification(UUID candidateId, CandidateCertificationDTO candidateCertificationDTO) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + candidateId));

        CandidateCertification candidateCertification = modelMapper.map(candidateCertificationDTO, CandidateCertification.class);
        candidateCertification.setCandidateId(candidate.getCandidateId());

        CandidateCertification savedCertification = candidateCertificationRepository.save(candidateCertification);
        log.info("New Candidate Certification has been created for candidate: {}", savedCertification.getCandidateId());
        modelMapper.map(savedCertification, CandidateCertificationDTO.class);
    }

    @Override
    public void deleteCandidateCertification(Integer certificationId) {
        candidateCertificationRepository.deleteById(certificationId);
        log.info("Candidate Certification has been deleted: {}", certificationId);
    }

    @Override
    public CandidateCertificationDTO retrieveCandidateCertification(Integer certificationId) {
        CandidateCertification certification = candidateCertificationRepository.findById(certificationId)
                .orElseThrow(() -> new CandidateNotFoundException("Certification not found with id: " + certificationId));
        return modelMapper.map(certification, CandidateCertificationDTO.class);
    }

    @Override
    public List<CandidateCertificationDTO> retrieveCandidateCertificationsByCandidateId(UUID candidateId) {
        return candidateCertificationRepository.findByCandidateId(candidateId).stream()
                .map(cert -> modelMapper.map(cert, CandidateCertificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCandidateCertification(Integer certificationId, CandidateCertificationDTO candidateCertificationDTO) {
        CandidateCertification certification = candidateCertificationRepository.findById(certificationId)
            .orElseThrow(() -> new CandidateNotFoundException("Certification not found for update with id: " + certificationId));

        modelMapper.map(candidateCertificationDTO, certification);
        CandidateCertification updatedCertification = candidateCertificationRepository.save(certification);
        log.info("Candidate Certification has been updated: {}", updatedCertification.getCertificationId());
        modelMapper.map(updatedCertification, CandidateCertificationDTO.class);
    }
} 