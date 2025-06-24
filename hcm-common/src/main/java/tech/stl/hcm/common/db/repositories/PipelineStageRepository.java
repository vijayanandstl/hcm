package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.PipelineStage;

public interface PipelineStageRepository extends JpaRepository<PipelineStage, Integer> {
} 