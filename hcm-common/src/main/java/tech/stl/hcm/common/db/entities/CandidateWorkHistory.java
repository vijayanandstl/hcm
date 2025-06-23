package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "candidate_work_history", schema = "hcm")
public class CandidateWorkHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_history_id")
    private Integer workHistoryId;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "position_title", length = 100)
    private String positionTitle;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private String responsibilities;
} 