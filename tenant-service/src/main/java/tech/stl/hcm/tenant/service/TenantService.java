package tech.stl.hcm.tenant.service;

import tech.stl.hcm.common.dto.TenantDTO;
import java.util.UUID;
import java.util.List;

public interface TenantService {
    TenantDTO createTenant(TenantDTO tenantDto);
    TenantDTO getTenantById(UUID tenantId);
    void deleteTenant(UUID tenantId);
    TenantDTO updateTenant(UUID tenantId, TenantDTO tenantDto);
    List<TenantDTO> getAllTenants();
} 