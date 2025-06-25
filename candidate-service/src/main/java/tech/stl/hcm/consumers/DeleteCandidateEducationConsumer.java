package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateEducationService;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidateeducation.kafka.topic.delete}",
        groupId = "${candidateeducation.kafka.group-id}",
        valueType = Integer.class,
        enableProperty = "candidateeducation.kafka.enable"
)
public class DeleteCandidateEducationConsumer implements MessageHandler<Integer> {

    private final CandidateEducationService candidateEducationService;

    @Override
    public void handle(Integer educationId, String key) {
        try {
            MDC.put("educationId", educationId.toString());
            log.info("Consumed delete request for candidate education: {}", educationId);
            candidateEducationService.deleteCandidateEducation(educationId);
        } finally {
            MDC.clear();
        }
    }
} 