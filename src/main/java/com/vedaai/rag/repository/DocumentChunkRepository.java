package com.vedaai.rag.repository;

import com.vedaai.rag.entity.DocumentChunk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentChunkRepository
        extends JpaRepository<DocumentChunk, UUID> {
}