package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ApplicationDTO {
    private Integer applicationId;
    private UUID candidateId;
    private Integer requisitionId;
    private Integer statusId;
    private LocalDate appliedDate;
    private String source;
} 