package com.vedaai.rag.service.embedding;

import java.util.List;

public interface EmbeddingService {
    List<Float> generateEmbedding(String text);
    boolean supports(String provider);
}
