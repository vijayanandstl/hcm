package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByOrganizationId(UUID organizationId);
} 