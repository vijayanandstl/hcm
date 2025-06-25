package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class InterviewDTO {
    private Integer interviewId;
    private Integer applicationId;
    private UUID candidateId;
    private Integer requisitionId;
    private UUID interviewerId;
    private Integer statusId;
    private LocalDate scheduledDate;
    private String mode;
    private String location;
} 