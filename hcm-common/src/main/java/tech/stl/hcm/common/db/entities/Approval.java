package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "approval", schema = "hcm")
public class Approval extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id")
    private Integer approvalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisition_id", nullable = false)
    private JobRequisition jobRequisition;

    @Column(name = "approver_id", nullable = false)
    private UUID approverId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private ApprovalStatus status;

    @Column(name = "action_date", nullable = false)
    private LocalDate actionDate;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;
} 