package com.vedaai.rag.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "document_chunks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "chunk_index")
    private Integer chunkIndex;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(
            name = "embedding",
            columnDefinition = "vector(768)"
    )
    private float[] embedding;

    @Column(name = "start_offset")
    private Integer startOffset;

    @Column(name = "end_offset")
    private Integer endOffset;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}