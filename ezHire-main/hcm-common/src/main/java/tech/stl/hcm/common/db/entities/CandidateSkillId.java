package tech.stl.hcm.common.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CandidateSkillId implements Serializable {

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "skill_id")
    private Integer skillId;
} 