package com.vedaai.rag.service.embedding.provider;

import com.vedaai.rag.exception.EmbeddingException;
import com.vedaai.rag.service.embedding.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OllamaEmbeddingService
        implements EmbeddingService {

    private final EmbeddingModel embeddingModel;

    @Override
    public List<Float> generateEmbedding(String text) {
        if(text == null || text.isBlank()) {
            throw new EmbeddingException("Input text cannot be null or empty");
        }
        float[] embedding =
                embeddingModel.embed(text);

        List<Float> result =
                new ArrayList<>();

        for (float value : embedding) {
            result.add(value);
        }

        return result;
    }

    @Override
    public boolean supports(String provider) {

        return provider.equalsIgnoreCase(
                "ollama"
        );
    }
}