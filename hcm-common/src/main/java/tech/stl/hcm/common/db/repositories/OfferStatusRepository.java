package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.OfferStatus;

public interface OfferStatusRepository extends JpaRepository<OfferStatus, Integer> {
} 