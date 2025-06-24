package tech.stl.hcm.tenant.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.stl.hcm.common.db.entities.Tenant;
import tech.stl.hcm.common.db.repositories.TenantRepository;
import tech.stl.hcm.common.dto.TenantDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TenantDTO createTenant(TenantDTO tenantDto) {
        Optional<Tenant> existingTenant = tenantRepository.findByName(tenantDto.getName());
        if (existingTenant.isPresent()) {
            return modelMapper.map(existingTenant.get(), TenantDTO.class);
        }
        Tenant tenant = modelMapper.map(tenantDto, Tenant.class);
        tenant.setTenantId(UUID.randomUUID());
        tenant = tenantRepository.save(tenant);
        return modelMapper.map(tenant, TenantDTO.class);
    }

    @Override
    public TenantDTO getTenantById(UUID tenantId) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(tenantId);
        return tenantOptional.map(tenant -> modelMapper.map(tenant, TenantDTO.class)).orElse(null);
    }

    @Override
    public List<TenantDTO> getAllTenants() {
        return tenantRepository.findAll()
                .stream()
                .map(tenant -> modelMapper.map(tenant, TenantDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTenant(UUID tenantId) {
        tenantRepository.deleteById(tenantId);
    }

    @Override
    public TenantDTO updateTenant(UUID tenantId, TenantDTO tenantDto) {
        Optional<Tenant> tenantOptional = tenantRepository.findById(tenantId);
        if (tenantOptional.isPresent()) {
            Tenant existingTenant = tenantOptional.get();
            existingTenant.setName(tenantDto.getName());
            existingTenant = tenantRepository.save(existingTenant);
            return modelMapper.map(existingTenant, TenantDTO.class);
        }
        return null;
    }
} 