package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Organization;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    List<Organization> findByOrganizationId(UUID organizationId);
    Optional<Organization> findByName(String name);
} 