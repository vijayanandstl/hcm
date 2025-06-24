package tech.stl.hcm.common.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CandidateCertificationDTO {
    private Integer certificationId;
    private UUID candidateId;
    private String certificateName;
    private String issuedBy;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String certificationName;
    private String issuingOrganization;
} 