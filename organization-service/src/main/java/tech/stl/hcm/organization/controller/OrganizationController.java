package tech.stl.hcm.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.stl.hcm.common.dto.OrganizationDTO;
import tech.stl.hcm.organization.service.OrganizationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable UUID id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }
} 