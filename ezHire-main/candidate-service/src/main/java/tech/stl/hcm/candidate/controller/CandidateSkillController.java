package tech.stl.hcm.candidate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.db.entities.CandidateSkillId;
import tech.stl.hcm.common.dto.CandidateSkillDTO;
import tech.stl.hcm.candidate.service.CandidateSkillService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates/{candidateId}/skills")
@RequiredArgsConstructor
public class CandidateSkillController {

    private final CandidateSkillService skillService;

    @GetMapping
    public ResponseEntity<List<CandidateSkillDTO>> getSkillsForCandidate(
            @PathVariable UUID candidateId) {
        List<CandidateSkillDTO> skills = skillService.retrieveCandidateSkillsByCandidateId(candidateId);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<CandidateSkillDTO> getSkill(
            @PathVariable UUID candidateId,
            @PathVariable Integer skillId) {
        CandidateSkillId candidateSkillId = new CandidateSkillId(candidateId, skillId);
        return ResponseEntity.ok(skillService.retrieveCandidateSkill(candidateSkillId));
    }

} 