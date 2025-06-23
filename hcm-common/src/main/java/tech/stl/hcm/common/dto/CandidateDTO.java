package tech.stl.hcm.common.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CandidateDTO {
    private UUID candidateId;
    private UUID tenantId;
    private UUID organizationId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
}
