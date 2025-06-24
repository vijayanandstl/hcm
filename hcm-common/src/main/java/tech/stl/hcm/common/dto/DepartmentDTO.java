package tech.stl.hcm.common.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDTO {
    private Integer departmentId;
    private UUID tenantId;
    private UUID organizationId;
    private String name;
    private Integer parentDepartmentId;
    private DepartmentStatusDTO status;
} 