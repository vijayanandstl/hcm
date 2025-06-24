package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Department;
import tech.stl.hcm.common.db.entities.Organization;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByNameAndOrganization(String name, Organization organization);

    List<Department> findByOrganization(Organization organization);
    
    List<Department> findByParentDepartment(Department parentDepartment);
}