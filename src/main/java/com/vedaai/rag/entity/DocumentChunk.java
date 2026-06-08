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
    @GeneratedValue
    private UUID id;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "chunk_index")
    private Integer chunkIndex;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(
    name = "embedding",
    columnDefinition = "vector(768)"
)
private float[] embedding;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}