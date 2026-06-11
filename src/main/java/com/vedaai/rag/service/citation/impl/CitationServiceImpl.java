package com.vedaai.rag.service.citation.impl;

import com.vedaai.api.dto.response.SourceInfo;
import com.vedaai.rag.projection.SimilarChunkProjection;
import com.vedaai.rag.service.citation.CitationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitationServiceImpl
        implements CitationService {

    @Override
    public List<SourceInfo> buildSources(
            List<SimilarChunkProjection> chunks
    ) {

        return chunks.stream()
                .map(chunk ->
                        SourceInfo.builder()
                                .chunkId(chunk.getId())
                                .fileName(
                                        chunk.getDocName()
                                )
                                .chunkIndex(
                                        chunk.getChunkIndex()
                                )
                                .confidence(
                                        1 - chunk.getDistance()
                                )
                                .build()
                )
                .toList();
    }
}