package com.vedaai.rag.projection;

import java.util.UUID;

public interface SimilarChunkProjection {

    UUID getId();

    String getContent();

    Double getDistance();

    Integer getChunkIndex();

    String getDocName();
}