package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.JobRequisitionStatus;

import java.util.Optional;

public interface JobRequisitionStatusRepository extends JpaRepository<JobRequisitionStatus, Integer> {
    Optional<JobRequisitionStatus> findByName(String name);
} 