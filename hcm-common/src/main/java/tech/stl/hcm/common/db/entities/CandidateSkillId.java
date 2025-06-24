package tech.stl.hcm.common.db.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Embeddable
public class CandidateSkillId implements Serializable {
    private UUID candidateId;
    private Integer skillId;
} 