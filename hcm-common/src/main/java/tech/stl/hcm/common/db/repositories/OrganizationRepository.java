package tech.stl.hcm.common.db.repositories;

import tech.stl.hcm.common.db.entities.Organization;
import tech.stl.hcm.common.db.entities.OrganizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByName(String name);
    List<Organization> findByTenant_TenantId(UUID tenantId);
    List<Organization> findByStatus(OrganizationStatus status);
} 