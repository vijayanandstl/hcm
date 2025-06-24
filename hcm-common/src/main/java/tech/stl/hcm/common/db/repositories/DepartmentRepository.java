package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByOrganizationId(UUID organizationId);
}