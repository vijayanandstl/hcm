package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.OfferStatus;

import java.util.Optional;

public interface OfferStatusRepository extends JpaRepository<OfferStatus, Integer> {
    Optional<OfferStatus> findByName(String name);
} 