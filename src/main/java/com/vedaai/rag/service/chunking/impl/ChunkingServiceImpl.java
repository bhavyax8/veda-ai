package com.vedaai.rag.service.chunking.impl;

import com.vedaai.rag.service.chunking.ChunkingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChunkingServiceImpl
        implements ChunkingService {

    private static final int CHUNK_SIZE = 512;

    private static final int CHUNK_OVERLAP = 64;

    @Override
    public List<String> chunkText(String text) {

        List<String> chunks = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return chunks;
        }

        String cleanedText = text
                .replaceAll("\\s+", " ")
                .trim();

        String[] words = cleanedText.split(" ");

        int start = 0;

        while (start < words.length) {

            int end = Math.min(
                    start + CHUNK_SIZE,
                    words.length
            );

            StringBuilder chunkBuilder =
                    new StringBuilder();

            for (int i = start; i < end; i++) {

                chunkBuilder
                        .append(words[i])
                        .append(" ");
            }

            chunks.add(
                    chunkBuilder.toString().trim()
            );

            start += (CHUNK_SIZE - CHUNK_OVERLAP);
        }

        return chunks;
    }
}