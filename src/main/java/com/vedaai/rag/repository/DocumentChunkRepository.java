package com.vedaai.rag.repository;

import com.vedaai.rag.entity.DocumentChunk;
import com.vedaai.rag.projection.SimilarChunkProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocumentChunkRepository
                extends JpaRepository<DocumentChunk, UUID> {
        @Query(value = """
    SELECT
        dc.content AS content,
        dc.chunk_index AS chunkIndex,
        dc.doc_name AS docName,
        dc.embedding <=> CAST(:embedding AS vector) AS distance
    FROM document_chunks dc
    ORDER BY distance ASC
    LIMIT :limit
    """, nativeQuery = true)
List<SimilarChunkProjection> searchSimilarChunks(
        @Param("embedding") String embedding,
        @Param("limit") int limit
);
}