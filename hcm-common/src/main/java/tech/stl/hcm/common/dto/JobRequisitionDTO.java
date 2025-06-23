package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class JobRequisitionDTO {
    private Integer requisitionId;
    private UUID tenantId;
    private UUID organizationId;
    private Integer positionId;
    private Integer departmentId;
    private String title;
    private String location;
    private String employmentType;
    private LocalDate postedDate;
    private LocalDate closingDate;
    private JobRequisitionStatusDTO status;
    private UUID hiringManagerId;
    private UUID vendorId;
} 