package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.PositionStatus;

public interface PositionStatusRepository extends JpaRepository<PositionStatus, Integer> {
} 