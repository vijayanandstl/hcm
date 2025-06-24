package tech.stl.hcm.common.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class OnboardingDTO {
    private Integer onboardingId;
    private Integer offerId;
    private UUID candidateId;
    private Integer statusId;
    private LocalDate startDate;
    private LocalDate orientationDate;
} 