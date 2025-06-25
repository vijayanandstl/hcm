package tech.stl.hcm.common.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DepartmentDTO {
    private UUID departmentId;
    private UUID tenantId;
    private UUID organizationId;
    private String name;
    private UUID parentDepartmentId;
    private UUID statusId;
} 