package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class OrganizationDTO {
    private UUID organizationId;
    private UUID tenantId;
    private String name;
    private String address;
    private OrganizationStatusDTO status;
} 