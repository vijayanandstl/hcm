package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Organization;
import tech.stl.hcm.common.db.entities.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Optional<Skill> findBySkillNameAndOrganization(String skillName, Organization organization);
    List<Skill> findByOrganization(Organization organization);
} 