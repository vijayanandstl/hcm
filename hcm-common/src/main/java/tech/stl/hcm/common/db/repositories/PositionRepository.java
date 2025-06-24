package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Position;

public interface PositionRepository extends JpaRepository<Position, Integer> {
} 