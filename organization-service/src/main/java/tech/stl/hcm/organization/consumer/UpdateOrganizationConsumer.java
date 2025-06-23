package tech.stl.hcm.organization.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.common.dto.OrganizationDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;
import tech.stl.hcm.organization.service.OrganizationService;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${organization.kafka.topic.update}",
        groupId = "${organization.kafka.group-id}",
        valueType = OrganizationDTO.class,
        enableProperty = "organization.kafka.enable"
)
public class UpdateOrganizationConsumer implements MessageHandler<OrganizationDTO> {

    private final OrganizationService organizationService;

    @Override
    public void handle(OrganizationDTO organizationDTO, String key) {
        try {
            MDC.put("organizationId", organizationDTO.getOrganizationId().toString());
            log.info("Consumed update organization: {}", organizationDTO.getName());
            organizationService.updateOrganization(organizationDTO.getOrganizationId(), organizationDTO);
        } finally {
            MDC.clear();
        }
    }
} 