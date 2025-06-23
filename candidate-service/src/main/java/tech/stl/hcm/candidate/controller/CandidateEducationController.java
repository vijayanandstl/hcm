package tech.stl.hcm.candidate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.CandidateEducationDTO;
import tech.stl.hcm.candidate.service.CandidateEducationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates/{candidateId}/educations")
@RequiredArgsConstructor
public class CandidateEducationController {

    private final CandidateEducationService educationService;


    @GetMapping
    public ResponseEntity<List<CandidateEducationDTO>> getEducationsForCandidate(
            @PathVariable UUID candidateId) {
        List<CandidateEducationDTO> educations = educationService.retrieveCandidateEducationsByCandidateId(candidateId);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{educationId}")
    public ResponseEntity<CandidateEducationDTO> getEducation(
            @PathVariable UUID candidateId,
            @PathVariable Integer educationId) {
        return ResponseEntity.ok(educationService.retrieveCandidateEducation(educationId));
    }

} 