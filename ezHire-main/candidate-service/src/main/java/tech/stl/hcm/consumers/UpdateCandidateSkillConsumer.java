package tech.stl.hcm.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.stl.hcm.candidate.service.CandidateSkillService;
import tech.stl.hcm.common.dto.CandidateSkillDTO;
import tech.stl.hcm.message.broker.consumer.MessageHandler;
import tech.stl.hcm.message.broker.consumer.TopicListener;

@Slf4j
@Component
@RequiredArgsConstructor
@TopicListener(
        topic = "${candidateskill.kafka.topic.update}",
        groupId = "${candidateskill.kafka.group-id}",
        valueType = CandidateSkillDTO.class,
        enableProperty = "candidateskill.kafka.enable"
)
public class UpdateCandidateSkillConsumer implements MessageHandler<CandidateSkillDTO> {

    private final CandidateSkillService candidateSkillService;

    @Override
    public void handle(CandidateSkillDTO candidateSkill, String key) {
        try {
            MDC.put("candidateId", candidateSkill.getCandidateId().toString());
            log.info("Consumed update request for candidate skill for candidate: {}", candidateSkill.getCandidateId());
            candidateSkillService.updateCandidateSkill(candidateSkill);
        } finally {
            MDC.clear();
        }
    }
} 