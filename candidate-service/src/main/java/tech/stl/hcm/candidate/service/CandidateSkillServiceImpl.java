package tech.stl.hcm.candidate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.CandidateSkill;
import tech.stl.hcm.common.db.entities.CandidateSkillId;
import tech.stl.hcm.common.db.repositories.CandidateSkillRepository;
import tech.stl.hcm.common.dto.CandidateSkillDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateSkillServiceImpl implements CandidateSkillService {

    private final CandidateSkillRepository candidateSkillRepository;
    private final ModelMapper modelMapper;

    @Override
    public CandidateSkillDTO createCandidateSkill(CandidateSkillDTO candidateSkillDTO) {
        CandidateSkill candidateSkill = modelMapper.map(candidateSkillDTO, CandidateSkill.class);
        Candidate candidate = new Candidate();
        candidate.setCandidateId(candidateSkillDTO.getCandidateId());
        candidateSkill.setCandidate(candidate);
        candidateSkill = candidateSkillRepository.save(candidateSkill);
        log.info("New Candidate Skill has been created for candidate: {}", candidateSkill.getCandidate().getCandidateId());
        return modelMapper.map(candidateSkill, CandidateSkillDTO.class);
    }

    @Override
    public void deleteCandidateSkill(CandidateSkillId candidateSkillId) {
        candidateSkillRepository.deleteById(candidateSkillId);
        log.info("Candidate Skill has been deleted: {}", candidateSkillId);
    }

    @Override
    public CandidateSkillDTO retrieveCandidateSkill(CandidateSkillId candidateSkillId) {
        log.info("Candidate Skill retrieval event for skill: {}", candidateSkillId);
        return candidateSkillRepository.findById(candidateSkillId)
                .map(skill -> modelMapper.map(skill, CandidateSkillDTO.class))
                .orElse(null);
    }

    @Override
    public List<CandidateSkillDTO> retrieveCandidateSkillsByCandidateId(UUID candidateId) {
        return candidateSkillRepository.findByCandidate_CandidateId(candidateId)
                .stream()
                .map(skill -> modelMapper.map(skill, CandidateSkillDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateSkillDTO updateCandidateSkill(CandidateSkillDTO candidateSkillDTO) {
        CandidateSkillId candidateSkillId = new CandidateSkillId(candidateSkillDTO.getCandidateId(), candidateSkillDTO.getSkillId());
        CandidateSkill skill = candidateSkillRepository.findById(candidateSkillId).orElse(null);
        if (skill != null) {
            modelMapper.map(candidateSkillDTO, skill);
            skill = candidateSkillRepository.save(skill);
            log.info("Candidate Skill has been updated: {}", candidateSkillId);
            return modelMapper.map(skill, CandidateSkillDTO.class);
        } else {
            log.info("Candidate Skill not found for update: {}", candidateSkillId);
        }
        return null;
    }
} 