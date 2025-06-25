package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
} 