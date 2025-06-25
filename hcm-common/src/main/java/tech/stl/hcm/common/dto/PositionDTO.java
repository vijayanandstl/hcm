package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class PositionDTO {
    private Integer positionId;
    private UUID tenantId;
    private UUID organizationId;
    private Integer departmentId;
    private String title;
    private String location;
    private String description;
    private String employmentType;
    private PositionStatusDTO status;
    private Integer headcount;
} 