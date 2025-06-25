package tech.stl.hcm.tenant.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;
import tech.stl.hcm.tenant.service.TenantService;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${tenant.kafka.topic.delete}",
        groupId = "${tenant.kafka.group-id}",
        valueType = UUID.class,
        enableProperty = "tenant.kafka.enable"
)
public class DeleteTenantConsumer implements MessageHandler<UUID> {

    private final TenantService tenantService;

    @Override
    public void handle(UUID tenantId, String key) {
        try {
            MDC.put("tenantId", tenantId.toString());
            log.info("Consumed delete-tenant event for tenant: {}", tenantId);
            tenantService.deleteTenant(tenantId);
        } finally {
            MDC.clear();
        }
    }
} 