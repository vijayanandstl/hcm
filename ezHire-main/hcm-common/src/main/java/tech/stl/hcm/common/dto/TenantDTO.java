package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class TenantDTO {
    private UUID tenantId;
    private String name;
    private String domain;
} 