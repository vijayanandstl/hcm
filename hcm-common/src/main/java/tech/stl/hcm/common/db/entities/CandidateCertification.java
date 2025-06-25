package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "candidate_certification", schema = "hcm")
public class CandidateCertification extends BaseEntity {

    @Id
    private Integer certificationId;

    @JoinColumn(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "certificate_name", length = 200)
    private String certificateName;

    @Column(name = "issued_by", length = 200)
    private String issuedBy;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "certification_name", length = 100)
    private String certificationName;

    @Column(name = "issuing_organization", length = 100)
    private String issuingOrganization;
} 