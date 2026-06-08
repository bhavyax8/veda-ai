package com.vedaai.rag.service.embedding.factory;

import com.vedaai.rag.exception.EmbeddingException;
import com.vedaai.rag.service.embedding.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmbeddingProviderFactory {

    private final List<EmbeddingService> providers;

    public EmbeddingService getProvider(
            String providerName) {

        return providers.stream()
                .filter(provider ->
                        provider.supports(providerName))
                .findFirst()
                .orElseThrow(() ->
                        new EmbeddingException(
                                "Unsupported embedding provider"
                        ));
    }
}