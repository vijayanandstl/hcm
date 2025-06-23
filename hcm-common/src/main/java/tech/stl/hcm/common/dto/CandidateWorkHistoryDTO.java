package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CandidateWorkHistoryDTO {
    private Integer workHistoryId;
    private UUID candidateId;
    private String companyName;
    private String positionTitle;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String responsibilities;
} 