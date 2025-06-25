package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class VendorDTO {
    private UUID vendorId;
    private UUID tenantId;
    private UUID organizationId;
    private String vendorName;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private VendorStatusDTO status;
} 