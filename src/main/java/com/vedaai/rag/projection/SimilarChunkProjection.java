package com.vedaai.rag.projection;

public interface SimilarChunkProjection {

    String getContent();

    Double getDistance();

    Integer getChunkIndex();

    String getDocName();
}