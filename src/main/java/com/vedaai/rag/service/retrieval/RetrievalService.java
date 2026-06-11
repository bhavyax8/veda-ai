package com.vedaai.rag.service.retrieval;

import java.util.List;

import com.vedaai.api.dto.request.RetrievalRequest;
import com.vedaai.api.dto.response.RetrievalResponse;
import com.vedaai.rag.projection.SimilarChunkProjection;

public interface RetrievalService {

    RetrievalResponse retrieve(RetrievalRequest request);
    List<SimilarChunkProjection> retrieveChunks(
            RetrievalRequest request
    );
}