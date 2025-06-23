package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "interview", schema = "hcm")
public class Interview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Integer interviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisition_id", nullable = false)
    private JobRequisition jobRequisition;

    @Column(name = "interviewer_id", nullable = false)
    private UUID interviewerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private InterviewStatus status;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "mode", length = 50)
    private String mode;

    @Column(name = "location", length = 200)
    private String location;
} 