package com.vedaai.rag.exception;

public class DatabaseException
        extends RuntimeException {

    public DatabaseException(
            String message) {

        super(message);
    }
}