package com.vedaai.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RetrievalResponse {

    private String query;

    private List<ChunkResult> results;
}