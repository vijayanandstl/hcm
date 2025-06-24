package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.InterviewStatus;

import java.util.List;
import java.util.UUID;

public interface InterviewStatusRepository extends JpaRepository<InterviewStatus, Integer> {
    List<InterviewStatus> findByOrganizationId(UUID organizationId);
} 