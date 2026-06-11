package com.vedaai.rag.service.citation;

import com.vedaai.api.dto.response.SourceInfo;
import com.vedaai.rag.projection.SimilarChunkProjection;

import java.util.List;

public interface CitationService {

    List<SourceInfo> buildSources(
            List<SimilarChunkProjection> chunks
    );
}