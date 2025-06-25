package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Onboarding;

public interface OnboardingRepository extends JpaRepository<Onboarding, Integer> {
} 