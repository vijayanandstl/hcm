package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.InterviewStatus;

import java.util.Optional;

public interface InterviewStatusRepository extends JpaRepository<InterviewStatus, Integer> {
    Optional<InterviewStatus> findByName(String name);
} 