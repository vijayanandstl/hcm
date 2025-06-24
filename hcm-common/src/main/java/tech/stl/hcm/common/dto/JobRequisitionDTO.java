package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class JobRequisitionDTO {
    private Integer requisitionId;
    private Integer tenantId;
    private Integer organizationId;
    private Integer positionId;
    private Integer departmentId;
    private String title;
    private String location;
    private String employmentType;
    private LocalDate postedDate;
    private LocalDate closingDate;
    private Integer statusId;
    private UUID hiringManagerId;
    private UUID vendorId;
} 