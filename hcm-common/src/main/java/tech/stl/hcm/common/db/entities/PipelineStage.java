package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pipeline_stage", schema = "hcm")
public class PipelineStage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Integer stageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisition_id", nullable = false)
    private JobRequisition jobRequisition;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;
} 