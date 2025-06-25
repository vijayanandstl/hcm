package tech.stl.hcm.tenant.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.common.dto.TenantDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;
import tech.stl.hcm.tenant.service.TenantService;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${tenant.kafka.topic.create}",
        groupId = "${tenant.kafka.group-id}",
        valueType = TenantDTO.class,
        enableProperty = "tenant.kafka.enable"
)
public class CreateTenantConsumer implements MessageHandler<TenantDTO> {

    private final TenantService tenantService;

    @Override
    public void handle(TenantDTO tenantDTO, String key) {
        try {
            MDC.put("tenantId", tenantDTO.getTenantId().toString());
            log.info("Consumed create-tenant event for tenant: {}", tenantDTO.getTenantId());
            tenantService.createTenant(tenantDTO);
        } finally {
            MDC.clear();
        }
    }
} 