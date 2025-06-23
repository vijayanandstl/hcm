package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.ApplicationStatus;

import java.util.Optional;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Integer> {
    Optional<ApplicationStatus> findByName(String name);
} 