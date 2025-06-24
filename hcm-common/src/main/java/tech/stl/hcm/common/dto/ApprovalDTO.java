package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ApprovalDTO {
    private Integer approvalId;
    private Integer requisitionId;
    private UUID approverId;
    private Integer statusId;
    private LocalDate actionDate;
    private String comments;
} 