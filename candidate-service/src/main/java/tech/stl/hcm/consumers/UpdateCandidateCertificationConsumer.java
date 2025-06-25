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
        topic = "${candidatecertificate.kafka.topic.update}",
        groupId = "${candidatecertificate.kafka.group-id}",
        valueType = CandidateCertificationDTO.class,
        enableProperty = "candidatecertificate.kafka.enable"
)
public class UpdateCandidateCertificationConsumer implements MessageHandler<CandidateCertificationDTO> {

    private final CandidateCertificationService candidateCertificationService;

    @Override
    public void handle(CandidateCertificationDTO candidateCertification, String key) {
        try {
            MDC.put("certificationId", candidateCertification.getCertificationId().toString());
            log.info("Consumed update request for candidate certification: {}", candidateCertification.getCertificationId());
            candidateCertificationService.updateCandidateCertification(candidateCertification.getCertificationId(), candidateCertification);
        } finally {
            MDC.clear();
        }
    }
} 