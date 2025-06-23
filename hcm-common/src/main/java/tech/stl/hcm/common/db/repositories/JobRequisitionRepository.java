package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Department;
import tech.stl.hcm.common.db.entities.JobRequisition;
import tech.stl.hcm.common.db.entities.Organization;

import java.util.List;

public interface JobRequisitionRepository extends JpaRepository<JobRequisition, Integer> {
    List<JobRequisition> findByOrganization(Organization organization);
    List<JobRequisition> findByDepartment(Department department);
} 