package com.vedaai.rag.service.ingestion.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.vedaai.rag.entity.DocumentChunk;
import com.vedaai.rag.repository.DocumentChunkRepository;
import com.vedaai.rag.service.chunking.ChunkingService;
import com.vedaai.rag.service.embedding.EmbeddingService;
import com.vedaai.rag.service.extraction.TextExtractionService;
import com.vedaai.rag.service.ingestion.DocumentIngestionService;
import com.vedaai.rag.validation.FileValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentIngestionServiceImpl implements DocumentIngestionService {
    private final TextExtractionService extractionService;
    private final ChunkingService chunkingService;
    private final EmbeddingService embeddingService;
    private final FileValidationService validator;
    private final DocumentChunkRepository documentChunkRepository;

    // private final Repository repository;
    @Override
    public void ingestDocument(MultipartFile file) {
        validator.validate(file);

        String extractedText = extractionService.extractText(file);

        List<String> chunks = chunkingService.chunkText(extractedText);
        List<DocumentChunk> entities = new ArrayList<>();

        for (int i = 0; i < chunks.size(); i++) {

            String chunk = chunks.get(i);

            List<Float> embedding = embeddingService
                    .generateEmbedding(chunk);

            float[] embeddingArray = new float[embedding.size()];

            for (int j = 0; j < embedding.size(); j++) {

                embeddingArray[j] = embedding.get(j);
            }

            DocumentChunk entity = DocumentChunk.builder()
                    .docName(
                            file.getOriginalFilename())
                    .chunkIndex(i)
                    .content(chunk)
                    .embedding(embeddingArray)
                    .createdAt(
                            LocalDateTime.now())
                    .build();

            entities.add(entity);
        }

        documentChunkRepository.saveAll(entities);
    }
}
