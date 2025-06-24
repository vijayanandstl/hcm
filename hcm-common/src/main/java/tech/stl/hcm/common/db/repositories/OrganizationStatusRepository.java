package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.OrganizationStatus;

public interface OrganizationStatusRepository extends JpaRepository<OrganizationStatus, Integer> {
} 