package tech.stl.hcm.common.db.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_role", schema = "hcm")
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "permissions", columnDefinition = "jsonb")
    private String permissions; // Representing JSONB as String
} 