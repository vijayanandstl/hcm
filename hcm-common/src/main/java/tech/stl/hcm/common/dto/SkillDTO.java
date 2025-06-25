package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class SkillDTO {
    private Integer skillId;
    private UUID tenantId;
    private UUID organizationId;
    private String skillName;
    private String skillCategory;
    private String description;
} 