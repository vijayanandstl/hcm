package tech.stl.hcm;

import tech.stl.hcm.common.dto.CandidateDTO;
import tech.stl.hcm.message.broker.producer.ProducerFactory;
import tech.stl.hcm.message.broker.producer.ProducerService;

import java.util.UUID;

public class CreateCandidateConsumerTest {

    private static final String topicName = "create-candidate";

    public static void main(String[] args) {
        ProducerFactory producerFactory = new ProducerFactory();
        ProducerService producerService = new ProducerService(producerFactory);
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId(UUID.randomUUID());
        candidateDTO.setTenantId(UUID.fromString("a15104c0-44b7-4512-b9b1-6122e7af7d41"));
        candidateDTO.setFirstName("John4");
        candidateDTO.setLastName("Doe4");
        candidateDTO.setEmail("john4.doe1@example.com");
        candidateDTO.setPhone("+919870345744");
        candidateDTO.setOrganizationId(UUID.fromString("08b06d14-4e03-11f0-bc56-325096b39f47"));
        String key = UUID.randomUUID().toString();
        producerService.publishMessage(topicName, key, candidateDTO);
    }
} 