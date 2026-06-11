package com.vedaai.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class SourceInfo {

    private UUID chunkId;

    private String fileName;

    private Integer chunkIndex;

    private Double confidence;
}