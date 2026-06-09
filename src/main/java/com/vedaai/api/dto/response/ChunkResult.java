package com.vedaai.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChunkResult {

    private String chunk;

    private Double score;
}