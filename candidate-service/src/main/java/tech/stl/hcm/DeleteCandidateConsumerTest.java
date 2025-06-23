package tech.stl.hcm;

import tech.stl.hcm.message.broker.producer.ProducerFactory;
import tech.stl.hcm.message.broker.producer.ProducerService;

import java.util.UUID;

public class DeleteCandidateConsumerTest {

    private static final String topicName = "delete-candidate";

    public static void main(String[] args) {
        ProducerFactory producerFactory = new ProducerFactory();
        ProducerService producerService = new ProducerService(producerFactory);
        String key = UUID.randomUUID().toString();
        UUID candidateId = UUID.fromString("6244c8de-e537-44d9-9cd7-b6f8d446992d");
        producerService.publishMessage(topicName, key, candidateId.toString());
    }
} 