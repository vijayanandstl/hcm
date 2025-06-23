package tech.stl.hcm.tenant;

import tech.stl.hcm.common.dto.TenantDTO;
import tech.stl.hcm.message.broker.producer.ProducerFactory;
import tech.stl.hcm.message.broker.producer.ProducerService;

import java.util.UUID;

public class CreateTenantConsumerTest {

    private static final String topicName = "create-tenant";

    public static void main(String[] args) {

        ProducerFactory producerFactory = new ProducerFactory();
        ProducerService producerService = new ProducerService(producerFactory);

        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setTenantId(UUID.randomUUID());
        tenantDTO.setName("Test Tenant from Manual Trigger");

        String key = UUID.randomUUID().toString();
        producerService.publishMessage(topicName, key, tenantDTO);
    }
} 