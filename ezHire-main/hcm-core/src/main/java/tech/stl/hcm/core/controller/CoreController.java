package tech.stl.hcm.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.stl.hcm.common.dto.CandidateDTO;
import tech.stl.hcm.common.dto.TenantDTO;
import tech.stl.hcm.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import tech.stl.hcm.common.dto.CandidateSkillDTO;
import tech.stl.hcm.common.dto.CandidateEducationDTO;
import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;
import tech.stl.hcm.common.dto.CandidateCertificationDTO;
import tech.stl.hcm.common.dto.OrganizationDTO;
import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class CoreController {

    private final CoreService coreService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from hcm-core!";
    }

    @GetMapping("/candidates")
    public List<CandidateDTO> getAllCandidates() {
        return coreService.getAllCandidates();
    }

    @GetMapping("/candidates/{candidateId}")
    public CandidateDTO getCandidateById(@PathVariable String candidateId) {
        return coreService.getCandidateById(candidateId);
    }

    @GetMapping("/candidates/{candidateId}/skills")
    public List<CandidateSkillDTO> getSkillsForCandidate(@PathVariable String candidateId) {
        return coreService.getSkillsForCandidate(candidateId);
    }

    @GetMapping("/candidates/{candidateId}/educations")
    public List<CandidateEducationDTO> getEducationsForCandidate(@PathVariable String candidateId) {
        return coreService.getEducationsForCandidate(candidateId);
    }

    @GetMapping("/candidates/{candidateId}/work-histories")
    public List<CandidateWorkHistoryDTO> getWorkHistoriesForCandidate(@PathVariable String candidateId) {
        return coreService.getWorkHistoriesForCandidate(candidateId);
    }

    @GetMapping("/candidates/{candidateId}/certifications")
    public List<CandidateCertificationDTO> getCertificationsForCandidate(@PathVariable String candidateId) {
        return coreService.getCertificationsForCandidate(candidateId);
    }

    @GetMapping("/tenants")
    public List<TenantDTO> getAllTenants() {
        return coreService.getAllTenants();
    }

    @GetMapping("/tenants/{tenantId}")
    public TenantDTO getTenantById(@PathVariable String tenantId) {
        return coreService.getTenantById(tenantId);
    }

    @GetMapping("/organizations")
    public List<OrganizationDTO> getAllOrganizations() {
        return coreService.getAllOrganizations();
    }

    @GetMapping("/organizations/{organizationId}")
    public OrganizationDTO getOrganizationById(@PathVariable String organizationId) {
        return coreService.getOrganizationById(organizationId);
    }

    @PostMapping("/publish")
    public void publishMessage(@RequestBody String message) {
        coreService.publishEvent("test-topic", "test-key", message);
    }

    @PostMapping("/candidates")
    public void createCandidate(@RequestBody CandidateDTO candidate) {
        coreService.createCandidate(candidate);
    }

    @PostMapping("/candidates/skills")
    public void addCandidateSkill(@RequestBody CandidateSkillDTO skill) {
        coreService.addCandidateSkill(skill);
    }

    @PostMapping("/candidates/educations")
    public void addCandidateEducation(@RequestBody CandidateEducationDTO education) {
        coreService.addCandidateEducation(education);
    }

    @PostMapping("/candidates/work-histories")
    public void addCandidateWorkHistory(@RequestBody CandidateWorkHistoryDTO workHistory) {
        coreService.addCandidateWorkHistory(workHistory);
    }

    @PutMapping("/candidates/{candidateId}")
    public void updateCandidate(@PathVariable String candidateId, @RequestBody CandidateDTO candidate) {
        coreService.updateCandidate(candidateId, candidate);
    }

    @PutMapping("/candidates/{candidateId}/skills/{skillId}")
    public void updateCandidateSkill(@PathVariable String candidateId, @PathVariable String skillId, @RequestBody CandidateSkillDTO skill) {
        coreService.updateCandidateSkill(candidateId, skillId, skill);
    }

    @PutMapping("/candidates/{candidateId}/educations/{educationId}")
    public void updateCandidateEducation(@PathVariable String candidateId, @PathVariable String educationId, @RequestBody CandidateEducationDTO education) {
        coreService.updateCandidateEducation(candidateId, educationId, education);
    }

    @DeleteMapping("/candidates/{candidateId}")
    public void deleteCandidate(@PathVariable String candidateId) {
        coreService.deleteCandidate(candidateId);
    }

    @DeleteMapping("/candidates/{candidateId}/skills/{skillId}")
    public void deleteCandidateSkill(@PathVariable String candidateId, @PathVariable String skillId) {
        coreService.deleteCandidateSkill(candidateId, skillId);
    }

    @DeleteMapping("/candidates/{candidateId}/educations/{educationId}")
    public void deleteCandidateEducation(@PathVariable String candidateId, @PathVariable String educationId) {
        coreService.deleteCandidateEducation(candidateId, educationId);
    }

    @PostMapping("/tenants")
    public void createTenant(@RequestBody TenantDTO tenant) {
        coreService.createTenant(tenant);
    }

    @PutMapping("/tenants/{tenantId}")
    public void updateTenant(@PathVariable String tenantId, @RequestBody TenantDTO tenant) {
        coreService.updateTenant(tenantId, tenant);
    }

    @DeleteMapping("/tenants/{tenantId}")
    public void deleteTenant(@PathVariable String tenantId) {
        coreService.deleteTenant(tenantId);
    }

    @PostMapping("/organizations")
    public void createOrganization(@RequestBody OrganizationDTO organization) {
        coreService.createOrganization(organization);
    }

    @PutMapping("/organizations/{organizationId}")
    public void updateOrganization(@PathVariable String organizationId, @RequestBody OrganizationDTO organization) {
        coreService.updateOrganization(organizationId, organization);
    }

    @DeleteMapping("/organizations/{organizationId}")
    public void deleteOrganization(@PathVariable String organizationId) {
        coreService.deleteOrganization(organizationId);
    }
} 