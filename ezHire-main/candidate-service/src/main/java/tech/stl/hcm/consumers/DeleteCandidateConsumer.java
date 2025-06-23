package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateService;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidate.kafka.topic.delete}",
        groupId = "${candidate.kafka.group-id}",
        valueType = UUID.class,
        enableProperty = "candidate.kafka.enable"
)
public class DeleteCandidateConsumer implements MessageHandler<UUID> {

    private final CandidateService candidateService;

    @Override
    public void handle(UUID candidateId, String key) {
        try {
            MDC.put("candidateId", candidateId.toString());
            log.info("Consumed delete request for candidate: {}", candidateId);
            candidateService.deleteCandidate(candidateId);
        } finally {
            MDC.clear();
        }
    }
} 