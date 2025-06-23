package tech.stl.hcm.common.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tenant", schema = "hcm")
public class Tenant extends BaseEntity {

    @Id
    @Column(name = "tenant_id")
    private UUID tenantId;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "domain", length = 200, nullable = false, unique = true)
    private String domain;
} 