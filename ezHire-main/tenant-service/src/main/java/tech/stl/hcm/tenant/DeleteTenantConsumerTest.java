package tech.stl.hcm.tenant;

import tech.stl.hcm.message.broker.producer.ProducerFactory;
import tech.stl.hcm.message.broker.producer.ProducerService;

import java.util.UUID;

public class DeleteTenantConsumerTest {

    private static final String topicName = "delete-tenant";

    public static void main(String[] args) {

        ProducerFactory producerFactory = new ProducerFactory();
        ProducerService producerService = new ProducerService(producerFactory);

        UUID tenantId = UUID.fromString("a15104c0-44b7-4512-b9b1-6122e7af7d41"); // Example UUID

        producerService.publishMessage(topicName, tenantId.toString(), tenantId);
    }
} 