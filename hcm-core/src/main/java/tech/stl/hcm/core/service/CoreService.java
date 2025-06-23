package tech.stl.hcm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tech.stl.hcm.common.dto.CandidateDTO;
import tech.stl.hcm.common.dto.CandidateSkillDTO;
import tech.stl.hcm.common.dto.TenantDTO;
import tech.stl.hcm.common.dto.OrganizationDTO;
import tech.stl.hcm.core.config.ServiceProperties;
import tech.stl.hcm.message.broker.producer.ProducerService;
import tech.stl.hcm.common.dto.CandidateEducationDTO;
import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;
import tech.stl.hcm.common.dto.CandidateCertificationDTO;
import java.util.List;

@Service
public class CoreService {

    private final WebClient webClient;
    private final ProducerService producerService;
    private final ServiceProperties serviceProperties;

    public CoreService(WebClient.Builder webClientBuilder, ProducerService producerService, ServiceProperties serviceProperties) {
        this.webClient = webClientBuilder.build();
        this.producerService = producerService;
        this.serviceProperties = serviceProperties;
    }

    public List<CandidateDTO> getAllCandidates() {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl())
                .retrieve()
                .bodyToFlux(CandidateDTO.class)
                .collectList()
                .block();
    }

    public CandidateDTO getCandidateById(String candidateId) {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl() + "/{candidateId}", candidateId)
                .retrieve()
                .bodyToMono(CandidateDTO.class)
                .block();
    }

    public List<TenantDTO> getAllTenants() {
        return webClient.get()
                .uri(serviceProperties.getTenantUrl())
                .retrieve()
                .bodyToFlux(TenantDTO.class)
                .collectList()
                .block();
    }

    public TenantDTO getTenantById(String tenantId) {
        return webClient.get()
                .uri(serviceProperties.getTenantUrl() + "/{tenantId}", tenantId)
                .retrieve()
                .bodyToMono(TenantDTO.class)
                .block();
    }

    public List<OrganizationDTO> getAllOrganizations() {
        return webClient.get()
                .uri(serviceProperties.getOrganizationUrl())
                .retrieve()
                .bodyToFlux(OrganizationDTO.class)
                .collectList()
                .block();
    }

    public OrganizationDTO getOrganizationById(String organizationId) {
        return webClient.get()
                .uri(serviceProperties.getOrganizationUrl() + "/{organizationId}", organizationId)
                .retrieve()
                .bodyToMono(OrganizationDTO.class)
                .block();
    }

    public List<CandidateSkillDTO> getSkillsForCandidate(String candidateId) {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl() + "/{candidateId}/skills", candidateId)
                .retrieve()
                .bodyToFlux(CandidateSkillDTO.class)
                .collectList()
                .block();
    }

    public List<CandidateEducationDTO> getEducationsForCandidate(String candidateId) {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl() + "/{candidateId}/educations", candidateId)
                .retrieve()
                .bodyToFlux(CandidateEducationDTO.class)
                .collectList()
                .block();
    }

    public List<CandidateWorkHistoryDTO> getWorkHistoriesForCandidate(String candidateId) {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl() + "/{candidateId}/work-histories", candidateId)
                .retrieve()
                .bodyToFlux(CandidateWorkHistoryDTO.class)
                .collectList()
                .block();
    }

    public List<CandidateCertificationDTO> getCertificationsForCandidate(String candidateId) {
        return webClient.get()
                .uri(serviceProperties.getCandidateUrl() + "/{candidateId}/certifications", candidateId)
                .retrieve()
                .bodyToFlux(CandidateCertificationDTO.class)
                .collectList()
                .block();
    }

    public <T> void publishEvent(String topic, String key, T payload) {
        producerService.publishMessage(topic, key, payload);
    }

    public void createCandidate(CandidateDTO candidate) {
        publishEvent("create-candidate", candidate.getEmail(), candidate);
    }

    public void updateCandidate(String candidateId, CandidateDTO candidate) {
        publishEvent("update-candidate", candidateId, candidate);
    }

    public void deleteCandidate(String candidateId) {
        publishEvent("delete-candidate", candidateId, null);
    }

    public void addCandidateSkill(CandidateSkillDTO skill) {
        publishEvent("create-candidate-skill", skill.getSkillId().toString(), skill);
    }

    public void addCandidateEducation(CandidateEducationDTO education) {
        publishEvent("create-candidate-education", education.getEducationId().toString(), education);
    }

    public void addCandidateWorkHistory(CandidateWorkHistoryDTO workHistory) {
        publishEvent("create-candidate-work-history", workHistory.getWorkHistoryId().toString(), workHistory);
    }

    public void addCandidateCertification(CandidateCertificationDTO certification) {
        publishEvent("create-candidate-certificate", certification.getCertificationId().toString(), certification);
    }

    public void updateCandidateSkill(String candidateId, String skillId, CandidateSkillDTO skill) {
        publishEvent("update-candidate-skill", candidateId + ":" + skillId, skill);
    }

    public void updateCandidateEducation(String candidateId, String educationId, CandidateEducationDTO education) {
        publishEvent("update-candidate-education", candidateId + ":" + educationId, education);
    }

    public void deleteCandidateSkill(String candidateId, String skillId) {
        publishEvent("delete-candidate-skill", candidateId + ":" + skillId, null);
    }

    public void deleteCandidateEducation(String candidateId, String educationId) {
        publishEvent("delete-candidate-education", candidateId + ":" + educationId, null);
    }

    public void createTenant(TenantDTO tenant) {
        publishEvent("create-tenant", tenant.getTenantId().toString(), tenant);
    }

    public void updateTenant(String tenantId, TenantDTO tenant) {
        publishEvent("update-tenant", tenantId, tenant);
    }

    public void deleteTenant(String tenantId) {
        publishEvent("delete-tenant", tenantId, null);
    }

    public void createOrganization(OrganizationDTO organization) {
        publishEvent("create-organization", organization.getOrganizationId().toString(), organization);
    }

    public void updateOrganization(String organizationId, OrganizationDTO organization) {
        publishEvent("update-organization", organizationId, organization);
    }

    public void deleteOrganization(String organizationId) {
        publishEvent("delete-organization", organizationId, null);
    }

    public void updateCandidateWorkHistory(String candidateId, String workHistoryId, CandidateWorkHistoryDTO workHistory) {
        publishEvent("update-candidate-work-history", candidateId + ":" + workHistoryId, workHistory);
    }
} 