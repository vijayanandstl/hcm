package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.DepartmentStatus;

import java.util.Optional;

public interface DepartmentStatusRepository extends JpaRepository<DepartmentStatus, Integer> {
    Optional<DepartmentStatus> findByName(String name);
} 