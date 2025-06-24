package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "interview_feedback", schema = "hcm")
public class InterviewFeedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false)
    private Interview interview;

    @Column(name = "interviewer_id", nullable = false)
    private UUID interviewerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "rating")
    private Integer rating;
} 