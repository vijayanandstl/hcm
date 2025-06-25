package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "candidate_skill", schema = "hcm")
public class CandidateSkill extends BaseEntity {

    @EmbeddedId
    private CandidateSkillId id;

    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @Column(name = "proficiency_level", length = 50)
    private String proficiencyLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
} 