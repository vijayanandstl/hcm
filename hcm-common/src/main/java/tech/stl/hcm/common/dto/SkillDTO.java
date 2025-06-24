package tech.stl.hcm.common.dto;

import lombok.Data;

@Data
public class SkillDTO {
    private Integer skillId;
    private Integer tenantId;
    private Integer organizationId;
    private String skillName;
    private String skillCategory;
    private String description;
} 