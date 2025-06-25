package tech.stl.hcm.common.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class OfferDTO {
    private Integer offerId;
    private Integer applicationId;
    private UUID candidateId;    
    private Integer requisitionId;
    private Integer statusId;
    private BigDecimal salary;
    private String currency;
    private LocalDate offerDate;
    private LocalDate acceptanceDeadline;
} 