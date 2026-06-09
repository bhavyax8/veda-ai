package com.vedaai.rag.service.ingestion.impl;

import com.vedaai.api.dto.ChunkData;
import com.vedaai.rag.entity.DocumentChunk;
import com.vedaai.rag.repository.DocumentChunkRepository;
import com.vedaai.rag.service.chunking.ChunkingService;
import com.vedaai.rag.service.embedding.EmbeddingService;
import com.vedaai.rag.service.extraction.TextExtractionService;
import com.vedaai.rag.service.ingestion.DocumentIngestionService;
import com.vedaai.rag.validation.FileValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentIngestionServiceImpl
        implements DocumentIngestionService {

    private final TextExtractionService extractionService;

    private final ChunkingService chunkingService;

    private final EmbeddingService embeddingService;

    private final FileValidationService validator;

    private final DocumentChunkRepository documentChunkRepository;

    @Override
    public void ingestDocument(MultipartFile file) {

        validator.validate(file);

        log.info("Starting ingestion for file: {}",
                file.getOriginalFilename());

        String extractedText = extractionService.extractText(file);

        List<ChunkData> chunks = chunkingService.chunkText(extractedText);

        List<DocumentChunk> entities = new ArrayList<>();

        for (ChunkData chunk : chunks) {

            log.info("Generating embedding for chunk {}",
                    chunk.getChunkIndex());
            if (chunk.getContent() == null ||
                    chunk.getContent().isBlank()) {

                log.warn(
                        "Skipping empty chunk {}",
                        chunk.getChunkIndex());

                continue;
            }

            List<Float> embedding = embeddingService.generateEmbedding(
                    chunk.getContent());

            DocumentChunk entity = DocumentChunk.builder()
                    .docName(file.getOriginalFilename())
                    .chunkIndex(chunk.getChunkIndex())
                    .content(chunk.getContent())
                    .embedding(
                            toPrimitive(embedding))
                    .startOffset(
                            chunk.getStartOffset())
                    .endOffset(
                            chunk.getEndOffset())
                    .createdAt(LocalDateTime.now())
                    .build();

            entities.add(entity);
        }

        documentChunkRepository.saveAll(entities);

        log.info(
                "Successfully stored {} chunks for file: {}",
                entities.size(),
                file.getOriginalFilename());
    }

    /**
     * Convert List<Float> → float[]
     */
    private float[] toPrimitive(List<Float> list) {

        float[] array = new float[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}