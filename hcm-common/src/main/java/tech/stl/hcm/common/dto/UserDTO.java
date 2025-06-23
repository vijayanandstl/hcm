package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID userId;
    private UUID tenantId;
    private UUID organizationId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private UserRoleDTO role;
    private UserTypeDTO type;
    private UserStatusDTO status;
} 