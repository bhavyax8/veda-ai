package com.vedaai.rag.service.retrieval.impl;

import com.vedaai.api.dto.request.RetrievalRequest;
import com.vedaai.api.dto.response.ChunkResult;
import com.vedaai.api.dto.response.RetrievalResponse;
import com.vedaai.rag.projection.SimilarChunkProjection;
import com.vedaai.rag.repository.DocumentChunkRepository;
import com.vedaai.rag.service.embedding.EmbeddingService;
import com.vedaai.rag.service.ranking.RankingService;
import com.vedaai.rag.service.retrieval.RetrievalService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievalServiceImpl implements RetrievalService {
        private static final int TOP_K = 5;

        private final EmbeddingService embeddingService;
        private final DocumentChunkRepository chunkRepository;
        private final RankingService rankingService;

        @Override
        public RetrievalResponse retrieve(RetrievalRequest request) {
                String query = request.getQuery();
                List<Float> embedding = embeddingService.generateEmbedding(query);

                String vectorString = embedding.toString();

                List<SimilarChunkProjection> rows = chunkRepository.searchSimilarChunks(
                                vectorString,
                                TOP_K);

                List<ChunkResult> results = rows.stream()
                                .map(row -> ChunkResult.builder()
                                                .chunk(row.getContent())
                                                .score(rankingService.calculateSimilarity(row.getDistance()))
                                                .build())
                                .toList();

                return RetrievalResponse.builder()
                                .query(query)
                                .results(results)
                                .build();
        }

        @Override
        public List<SimilarChunkProjection> retrieveChunks(
                        RetrievalRequest request) {

                String query = request.getQuery();

                List<Float> embedding = embeddingService.generateEmbedding(
                                query);

                String vectorString = embedding.toString();

                return chunkRepository.searchSimilarChunks(
                                vectorString,
                                TOP_K);
        }
}