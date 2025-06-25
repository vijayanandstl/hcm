package tech.stl.hcm.organization.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;
import tech.stl.hcm.organization.service.OrganizationService;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${organization.kafka.topic.delete}",
        groupId = "${organization.kafka.group-id}",
        valueType = String.class,
        enableProperty = "organization.kafka.enable"
)
public class DeleteOrganizationConsumer implements MessageHandler<String> {

    private final OrganizationService organizationService;

    @Override
    public void handle(String organizationId, String key) {
        try {
            MDC.put("organizationId", organizationId);
            log.info("Consumed delete organization: {}", organizationId);
            organizationService.deleteOrganization(UUID.fromString(organizationId));
        } finally {
            MDC.clear();
        }
    }
} 