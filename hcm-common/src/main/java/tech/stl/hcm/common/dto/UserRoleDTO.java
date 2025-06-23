package tech.stl.hcm.common.dto;

import lombok.Data;

@Data
public class UserRoleDTO {
    private Integer roleId;
    private String name;
    private String permissions;
} 