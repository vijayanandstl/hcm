package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Application;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.Interview;

import java.util.List;
import java.util.UUID;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findByApplication(Application application);
    List<Interview> findByCandidate(Candidate candidate);
    List<Interview> findByInterviewerId(UUID interviewerId);
} 