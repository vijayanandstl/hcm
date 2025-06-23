package tech.stl.hcm.candidate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.CandidateDTO;
import tech.stl.hcm.candidate.service.CandidateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(candidateService.retrieveCandidate(candidateId));
    }
} 