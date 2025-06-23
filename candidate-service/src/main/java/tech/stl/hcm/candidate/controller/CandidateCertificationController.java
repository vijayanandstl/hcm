package tech.stl.hcm.candidate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.CandidateCertificationDTO;
import tech.stl.hcm.candidate.service.CandidateCertificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates/{candidateId}/certifications")
@RequiredArgsConstructor
public class CandidateCertificationController {

    private final CandidateCertificationService certificationService;


    @GetMapping
    public ResponseEntity<List<CandidateCertificationDTO>> getCertificationsForCandidate(
            @PathVariable UUID candidateId) {
        List<CandidateCertificationDTO> certifications = certificationService.retrieveCandidateCertificationsByCandidateId(candidateId);
        return ResponseEntity.ok(certifications);
    }

    @GetMapping("/{certificationId}")
    public ResponseEntity<CandidateCertificationDTO> getCertification(
            @PathVariable UUID candidateId,
            @PathVariable Integer certificationId) {
        // The candidateId is in the path but not strictly needed for retrieval by certificationId,
        // but it's good for RESTful practice. We could add a check to ensure the certification belongs to the candidate.
        return ResponseEntity.ok(certificationService.retrieveCandidateCertification(certificationId));
    }

} 