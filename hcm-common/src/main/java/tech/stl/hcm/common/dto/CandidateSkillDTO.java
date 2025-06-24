package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CandidateSkillDTO {
    private UUID candidateId;
    private UUID skillId;
    private String proficiencyLevel;
    private Integer yearsOfExperience;
} 