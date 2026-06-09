package com.vedaai.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChunkData {

    private Integer chunkIndex;

    private String content;

    private Integer startOffset;

    private Integer endOffset;
}