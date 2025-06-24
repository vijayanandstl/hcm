package tech.stl.hcm.common.db.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.ApprovalStatus;

public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Integer> {
    List<ApprovalStatus> findByOrganizationId(UUID organizationId);
} 