package tech.stl.hcm.common.dto;

import lombok.Data;

@Data
public class PositionDTO {
    private Integer positionId;
    private Integer tenantId;
    private Integer organizationId;
    private Integer departmentId;
    private String title;
    private String location;
    private String description;
    private String employmentType;
    private Integer statusId;
    private Integer headcount;
} 