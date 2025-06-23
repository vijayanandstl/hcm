package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.ApprovalStatus;

import java.util.Optional;

public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Integer> {
    Optional<ApprovalStatus> findByName(String name);
} 