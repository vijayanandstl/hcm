package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateWorkHistoryService;
import tech.stl.hcm.common.dto.CandidateWorkHistoryDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidateworkhistory.kafka.topic.update}",
        groupId = "${candidateworkhistory.kafka.group-id}",
        valueType = CandidateWorkHistoryDTO.class,
        enableProperty = "candidateworkhistory.kafka.enable"
)
public class UpdateCandidateWorkHistoryConsumer implements MessageHandler<CandidateWorkHistoryDTO> {

    private final CandidateWorkHistoryService candidateWorkHistoryService;

    @Override
    public void handle(CandidateWorkHistoryDTO candidateWorkHistory, String key) {
        try {
            MDC.put("workHistoryId", String.valueOf(candidateWorkHistory.getWorkHistoryId()));
            log.info("Consumed update request for candidate work history: {}", candidateWorkHistory.getWorkHistoryId());
            candidateWorkHistoryService.updateCandidateWorkHistory(candidateWorkHistory);
        } finally {
            MDC.clear();
        }
    }
} 