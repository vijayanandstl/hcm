package tech.stl.hcm.tenant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.TenantDTO;
import tech.stl.hcm.tenant.service.TenantService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public ResponseEntity<List<TenantDTO>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantDTO> getTenantById(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(tenantService.getTenantById(tenantId));
    }
} 