package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
} 