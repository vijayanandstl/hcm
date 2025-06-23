package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.OnboardingStatus;

import java.util.Optional;

public interface OnboardingStatusRepository extends JpaRepository<OnboardingStatus, Integer> {
    Optional<OnboardingStatus> findByName(String name);
} 