package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.DepartmentStatus;

import java.util.List;
import java.util.UUID;

public interface DepartmentStatusRepository extends JpaRepository<DepartmentStatus, Integer> {
    List<DepartmentStatus> findByOrganizationId(UUID organizationId);
} 