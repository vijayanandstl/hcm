package tech.stl.hcm.candidate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;
import tech.stl.hcm.candidate.service.CandidateWorkHistoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates/{candidateId}/work-histories")
@RequiredArgsConstructor
public class CandidateWorkHistoryController {

    private final CandidateWorkHistoryService workHistoryService;

    @GetMapping
    public ResponseEntity<List<CandidateWorkHistoryDTO>> getWorkHistoriesForCandidate(
            @PathVariable UUID candidateId) {
        List<CandidateWorkHistoryDTO> workHistories = workHistoryService.retrieveCandidateWorkHistoriesByCandidateId(candidateId);
        return ResponseEntity.ok(workHistories);
    }

    @GetMapping("/{workHistoryId}")
    public ResponseEntity<CandidateWorkHistoryDTO> getWorkHistory(
            @PathVariable UUID candidateId,
            @PathVariable Integer workHistoryId) {
        return ResponseEntity.ok(workHistoryService.retrieveCandidateWorkHistory(workHistoryId));
    }

} 