package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.JobRequisition;

import java.util.List;
import java.util.UUID;

public interface JobRequisitionRepository extends JpaRepository<JobRequisition, Integer> {
    List<JobRequisition> findByOrganizationId(UUID organizationId);
} 