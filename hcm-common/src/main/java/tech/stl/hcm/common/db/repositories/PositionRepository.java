package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Department;
import tech.stl.hcm.common.db.entities.Organization;
import tech.stl.hcm.common.db.entities.Position;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByTitleAndOrganization(String title, Organization organization);
    List<Position> findByOrganization(Organization organization);
    List<Position> findByDepartment(Department department);
} 