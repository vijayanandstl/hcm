package tech.stl.hcm.common.db.entities;

import java.util.UUID;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(name = "proficiency_level", length = 50)
    private String proficiencyLevel;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;
} 