package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateWorkHistoryService;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidateworkhistory.kafka.topic.delete}",
        groupId = "${candidateworkhistory.kafka.group-id}",
        valueType = Integer.class,
        enableProperty = "candidateworkhistory.kafka.enable"
)
public class DeleteCandidateWorkHistoryConsumer implements MessageHandler<Integer> {

    private final CandidateWorkHistoryService candidateWorkHistoryService;

    @Override
    public void handle(Integer workHistoryId, String key) {
        try {
            MDC.put("workHistoryId", workHistoryId.toString());
            log.info("Consumed delete request for candidate work history: {}", workHistoryId);
            candidateWorkHistoryService.deleteCandidateWorkHistory(workHistoryId);
        } finally {
            MDC.clear();
        }
    }
} 