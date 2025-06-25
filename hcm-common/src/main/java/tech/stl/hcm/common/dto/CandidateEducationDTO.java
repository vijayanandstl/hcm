package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CandidateEducationDTO {
    private UUID educationId;
    private UUID candidateId;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate endDate;
    private String grade;
    private String notes;
    private String description;
    private String institutionName;
} 