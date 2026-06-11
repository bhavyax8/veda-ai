package com.vedaai.rag.service.query.impl;

import com.vedaai.api.dto.request.QueryRequest;
import com.vedaai.api.dto.request.RetrievalRequest;
import com.vedaai.api.dto.response.QueryResponse;
import com.vedaai.api.dto.response.SourceInfo;
import com.vedaai.rag.projection.SimilarChunkProjection;
import com.vedaai.rag.service.citation.CitationService;
import com.vedaai.rag.service.groq.GroqService;
import com.vedaai.rag.service.prompt.PromptBuilderService;
import com.vedaai.rag.service.query.QueryService;
import com.vedaai.rag.service.retrieval.RetrievalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryServiceImpl
        implements QueryService {

    private final RetrievalService retrievalService;

    private final PromptBuilderService promptBuilderService;

    private final GroqService groqService;

    private final CitationService citationService;

    @Override
    public QueryResponse query(
            QueryRequest request
    ) {

        String question = request.getQuestion();

        List<SimilarChunkProjection> chunks =
                retrievalService.retrieveChunks(RetrievalRequest.builder()
                        .query(question)
                        .build());

        List<String> chunkContents =
                chunks.stream()
                        .map(
                                SimilarChunkProjection::getContent
                        )
                        .toList();

        String prompt =
                promptBuilderService.buildPrompt(
                        question,
                        chunkContents
                );

        String answer =
                groqService.generateAnswer(prompt);

        List<SourceInfo> sources =
                citationService.buildSources(chunks);

        return QueryResponse.builder()
                .question(question)
                .answer(answer)
                .sources(sources)
                .build();
    }
}