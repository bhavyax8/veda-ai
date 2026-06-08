package com.vedaai.rag.service.chunking;

import java.util.List;

public interface ChunkingService {
    List<String> chunkText(String text);
}
