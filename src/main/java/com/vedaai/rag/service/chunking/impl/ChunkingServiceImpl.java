package com.vedaai.rag.service.chunking.impl;

import com.vedaai.api.dto.ChunkData;
import com.vedaai.rag.service.chunking.ChunkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ChunkingServiceImpl
        implements ChunkingService {

    /**
     * Recommended values for resume/document RAG
     */
    private static final int CHUNK_SIZE = 350;

    private static final int CHUNK_OVERLAP = 75;

    @Override
    public List<ChunkData> chunkText(String text) {

        List<ChunkData> chunks = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return chunks;
        }

        /**
         * Normalize whitespace
         */
        String cleanedText = text
                .replaceAll("\\s+", " ")
                .trim();

        int start = 0;

        int chunkIndex = 0;

        while (start < cleanedText.length()) {

            int end = Math.min(
                    start + CHUNK_SIZE,
                    cleanedText.length()
            );

            /**
             * Try ending at nearest separator
             */
            if (end < cleanedText.length()) {

                int lastPeriod =
                        cleanedText.lastIndexOf(".", end);

                int lastComma =
                        cleanedText.lastIndexOf(",", end);

                int lastSpace =
                        cleanedText.lastIndexOf(" ", end);

                int bestEnd = Math.max(
                        lastPeriod,
                        Math.max(lastComma, lastSpace)
                );

                if (bestEnd > start) {
                    end = bestEnd + 1;
                }
            }

            String chunk =
                    cleanedText.substring(start, end)
                            .trim();

            /**
             * Skip tiny/noisy chunks
             */
            if (chunk.length() > 50) {

                chunks.add(
                        ChunkData.builder()
                                .chunkIndex(chunkIndex)
                                .content(chunk)
                                .startOffset(start)
                                .endOffset(end)
                                .build()
                );

                log.info(
                        "Created chunk {} size={}",
                        chunkIndex,
                        chunk.length()
                );

                chunkIndex++;
            }

            /**
             * overlap
             */
            start += (CHUNK_SIZE - CHUNK_OVERLAP);
        }

        log.info(
                "Total chunks created: {}",
                chunks.size()
        );

        return chunks;
    }
}