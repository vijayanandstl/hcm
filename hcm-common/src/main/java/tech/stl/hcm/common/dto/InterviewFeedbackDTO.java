package tech.stl.hcm.common.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class InterviewFeedbackDTO {
    private Integer feedbackId;
    private Integer interviewId;
    private UUID interviewerId;
    private UUID candidateId;
    private String feedback;
    private Integer rating;
} 