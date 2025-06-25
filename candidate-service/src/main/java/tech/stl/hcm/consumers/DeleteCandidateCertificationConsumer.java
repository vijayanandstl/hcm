package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateCertificationService;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidatecertificate.kafka.topic.delete}",
        groupId = "${candidatecertificate.kafka.group-id}",
        valueType = Integer.class,
        enableProperty = "candidatecertificate.kafka.enable"
)
public class DeleteCandidateCertificationConsumer implements MessageHandler<Integer> {

    private final CandidateCertificationService candidateCertificationService;

    @Override
    public void handle(Integer certificationId, String key) {
        try {
            MDC.put("certificationId", certificationId.toString());
            log.info("Consumed delete request for candidate certification: {}", certificationId);
            candidateCertificationService.deleteCandidateCertification(certificationId);
        } finally {
            MDC.clear();
        }
    }
} 