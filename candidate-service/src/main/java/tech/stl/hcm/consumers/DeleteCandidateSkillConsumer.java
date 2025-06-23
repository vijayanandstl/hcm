package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateSkillService;
import tech.stl.hcm.common.db.entities.CandidateSkillId;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidateskill.kafka.topic.delete}",
        groupId = "${candidateskill.kafka.group-id}",
        valueType = CandidateSkillId.class,
        enableProperty = "candidateskill.kafka.enable"
)
public class DeleteCandidateSkillConsumer implements MessageHandler<CandidateSkillId> {

    private final CandidateSkillService candidateSkillService;

    @Override
    public void handle(CandidateSkillId candidateSkillId, String key) {
        try {
            MDC.put("candidateId", candidateSkillId.getCandidateId().toString());
            log.info("Consumed delete request for candidate skill for candidate: {}", candidateSkillId.getCandidateId().toString());
            candidateSkillService.deleteCandidateSkill(candidateSkillId);
        } finally {
            MDC.clear();
        }
    }
} 