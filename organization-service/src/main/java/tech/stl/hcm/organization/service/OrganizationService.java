package tech.stl.hcm.organization.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.stl.hcm.common.db.entities.Organization;
import tech.stl.hcm.common.db.repositories.OrganizationRepository;
import tech.stl.hcm.common.dto.OrganizationDTO;
import tech.stl.hcm.common.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(org -> modelMapper.map(org, OrganizationDTO.class))
                .collect(Collectors.toList());
    }

    public OrganizationDTO getOrganizationById(UUID organizationId) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + organizationId));
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        Optional<Organization> existingOrganization = organizationRepository.findByName(organizationDTO.getName());
        if (existingOrganization.isPresent()) {
            return modelMapper.map(existingOrganization.get(), OrganizationDTO.class);
        }
        Organization organization = modelMapper.map(organizationDTO, Organization.class);
        organization = organizationRepository.save(organization);
        return modelMapper.map(organization, OrganizationDTO.class);
    }

    public OrganizationDTO updateOrganization(UUID organizationId, OrganizationDTO organizationDTO) {
        Organization existingOrganization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + organizationId));
        
        modelMapper.map(organizationDTO, existingOrganization);
        existingOrganization.setOrganizationId(organizationId); // Ensure the ID is not changed
        
        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        return modelMapper.map(updatedOrganization, OrganizationDTO.class);
    }

    public void deleteOrganization(UUID organizationId) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new ResourceNotFoundException("Organization not found with id: " + organizationId);
        }
        organizationRepository.deleteById(organizationId);
    }
} 