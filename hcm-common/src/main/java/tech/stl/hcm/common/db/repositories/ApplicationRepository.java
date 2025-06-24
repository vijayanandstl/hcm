package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Application;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.JobRequisition;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByCandidate(Candidate candidate);
    List<Application> findByJobRequisition(JobRequisition jobRequisition);
} 