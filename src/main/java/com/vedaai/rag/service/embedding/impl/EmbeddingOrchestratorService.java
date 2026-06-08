package com.vedaai.rag.service.embedding.impl;

import com.vedaai.rag.service.embedding.EmbeddingService;
import com.vedaai.rag.service.embedding.factory.EmbeddingProviderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmbeddingOrchestratorService {

    private final EmbeddingProviderFactory factory;

    @Value("${veda.embedding.provider}")
    private String provider;

    public List<Float> generateEmbedding(String text) {

        EmbeddingService embeddingService =
                factory.getProvider(provider);

        return embeddingService
                .generateEmbedding(text);
    }
}