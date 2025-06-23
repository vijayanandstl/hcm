package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Organization;
import tech.stl.hcm.common.db.entities.Tenant;
import tech.stl.hcm.common.db.entities.Vendor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByVendorNameAndOrganization(String vendorName, Organization organization);
    List<Vendor> findByTenant(Tenant tenant);
    List<Vendor> findByOrganization(Organization organization);
} 