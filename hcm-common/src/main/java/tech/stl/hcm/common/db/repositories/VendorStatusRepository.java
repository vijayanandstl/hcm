package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.VendorStatus;

import java.util.Optional;

public interface VendorStatusRepository extends JpaRepository<VendorStatus, Integer> {
    Optional<VendorStatus> findByName(String name);
} 