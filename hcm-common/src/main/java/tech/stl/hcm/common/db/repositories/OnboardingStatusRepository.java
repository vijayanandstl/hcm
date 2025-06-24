package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.OnboardingStatus;

public interface OnboardingStatusRepository extends JpaRepository<OnboardingStatus, Integer> {
} 