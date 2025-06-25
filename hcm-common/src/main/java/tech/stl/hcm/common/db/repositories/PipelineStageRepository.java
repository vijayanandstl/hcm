package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.JobRequisition;
import tech.stl.hcm.common.db.entities.PipelineStage;

import java.util.List;

public interface PipelineStageRepository extends JpaRepository<PipelineStage, Integer> {
    List<PipelineStage> findByJobRequisitionOrderBySequenceAsc(JobRequisition jobRequisition);
} 