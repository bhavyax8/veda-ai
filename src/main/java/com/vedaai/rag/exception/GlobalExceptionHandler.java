package com.vedaai.rag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vedaai.api.dto.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            FileValidationException.class
    )
    public ResponseEntity<ServerResponse>
    handleValidationException(
            FileValidationException ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(300);
        response.setMessage(
                "File validation failed"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(
            UnsupportedFileTypeException.class
    )
    public ResponseEntity<ServerResponse>
    handleUnsupportedFileException(
            UnsupportedFileTypeException ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(400);
        response.setMessage(
                "Unsupported file type"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(
            ExtractionException.class
    )
    public ResponseEntity<ServerResponse>
    handleExtractionException(
            ExtractionException ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(500);
        response.setMessage(
                "Text extraction failed"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }

    @ExceptionHandler(
            EmbeddingException.class
    )
    public ResponseEntity<ServerResponse>
    handleEmbeddingException(
            EmbeddingException ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(500);
        response.setMessage(
                "Embedding generation failed"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }

    @ExceptionHandler(
            DatabaseException.class
    )
    public ResponseEntity<ServerResponse>
    handleDatabaseException(
            DatabaseException ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(500);
        response.setMessage(
                "Database operation failed"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServerResponse>
    handleGenericException(
            Exception ex) {

        ServerResponse response =
                new ServerResponse();

        response.setStatus(500);
        response.setMessage(
                "Internal server error"
        );
        response.setError(ex.getMessage());

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }
}