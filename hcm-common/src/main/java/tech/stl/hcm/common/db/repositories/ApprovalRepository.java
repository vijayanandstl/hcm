package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Approval;
import tech.stl.hcm.common.db.entities.JobRequisition;

import java.util.List;
import java.util.UUID;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
    List<Approval> findByJobRequisition(JobRequisition jobRequisition);
    List<Approval> findByApproverId(UUID approverId);
} 