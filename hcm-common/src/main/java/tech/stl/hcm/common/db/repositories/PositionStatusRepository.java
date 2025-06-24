package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.PositionStatus;

import java.util.Optional;

public interface PositionStatusRepository extends JpaRepository<PositionStatus, Integer> {
    Optional<PositionStatus> findByName(String name);
} 