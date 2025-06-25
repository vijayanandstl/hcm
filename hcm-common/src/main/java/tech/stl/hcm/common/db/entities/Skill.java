package tech.stl.hcm.common.db.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "skill", schema = "hcm")
public class Skill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private UUID tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private UUID organizationId;

    @Column(name = "skill_name", length = 100)
    private String skillName;

    @Column(name = "skill_category", length = 100)
    private String skillCategory;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
} 