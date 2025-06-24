package tech.stl.hcm.consumers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateService;
import tech.stl.hcm.common.dto.CandidateDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidate.kafka.topic}",
        groupId = "${candidate.kafka.group-id}",
        valueType = CandidateDTO.class,
        enableProperty = "candidate.kafka.enable"
)
public class CreateCandidateConsumer implements MessageHandler<CandidateDTO> {
    private final CandidateService candidateService;

    @Override
    public void handle(CandidateDTO candidate, String key) {
        try {
            MDC.put("candidateId", candidate.getCandidateId().toString());
            log.info("Consumed candidate: {}", candidate.getEmail());
            candidateService.createCandidate(candidate);
        } finally {
            MDC.clear();
        }
    }
}
