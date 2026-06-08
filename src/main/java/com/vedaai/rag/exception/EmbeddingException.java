package com.vedaai.rag.exception;

public class EmbeddingException
        extends RuntimeException {

    public EmbeddingException(
            String message) {

        super(message);
    }
}