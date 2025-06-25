package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateCertificationService;
import tech.stl.hcm.common.dto.CandidateCertificationDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidatecertificate.kafka.topic}",
        groupId = "${candidatecertificate.kafka.group-id}",
        valueType = CandidateCertificationDTO.class,
        enableProperty = "candidatecertificate.kafka.enable"
)
public class CreateCandidateCertificationConsumer implements MessageHandler<CandidateCertificationDTO> {

    private final CandidateCertificationService candidateCertificationService;

    @Override
    public void handle(CandidateCertificationDTO candidateCertification, String key) {
        try {
            MDC.put("candidateId", candidateCertification.getCandidateId().toString());
            log.info("Consumed candidate certification for candidate: {}", candidateCertification.getCandidateId());
            candidateCertificationService.createCandidateCertification(candidateCertification.getCandidateId(), candidateCertification);
        } finally {
            MDC.clear();
        }
    }
} 