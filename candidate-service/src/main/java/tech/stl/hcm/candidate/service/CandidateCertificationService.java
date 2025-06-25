package tech.stl.hcm.candidate.service;

import tech.stl.hcm.common.dto.CandidateCertificationDTO;

import java.util.List;
import java.util.UUID;

public interface CandidateCertificationService {
    void createCandidateCertification(UUID candidateId, CandidateCertificationDTO candidateCertificationDTO);

    void deleteCandidateCertification(Integer certificationId);

    CandidateCertificationDTO retrieveCandidateCertification(Integer certificationId);

    List<CandidateCertificationDTO> retrieveCandidateCertificationsByCandidateId(UUID candidateId);

    void updateCandidateCertification(Integer certificationId, CandidateCertificationDTO candidateCertificationDTO);
} 