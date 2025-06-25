package tech.stl.hcm.common.dto;

import lombok.Data;

@Data
public class PipelineStageDTO {
    private Integer stageId;
    private Integer requisitionId;
    private String name;
    private Integer sequence;
} 