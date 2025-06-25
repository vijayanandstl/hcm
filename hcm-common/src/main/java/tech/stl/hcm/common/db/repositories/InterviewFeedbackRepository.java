package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Candidate;
import tech.stl.hcm.common.db.entities.Interview;
import tech.stl.hcm.common.db.entities.InterviewFeedback;

import java.util.List;
import java.util.UUID;

public interface InterviewFeedbackRepository extends JpaRepository<InterviewFeedback, Integer> {
    List<InterviewFeedback> findByInterview(Interview interview);
    List<InterviewFeedback> findByInterviewerId(UUID interviewerId);
    List<InterviewFeedback> findByCandidate(Candidate candidate);
} 