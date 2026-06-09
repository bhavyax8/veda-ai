package com.vedaai.rag.service.chunking;

import java.util.List;

import com.vedaai.api.dto.ChunkData;

public interface ChunkingService {
    List<ChunkData> chunkText(String text);
}
