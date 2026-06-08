package com.vedaai.rag.service.ingestion;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentIngestionService {
    void ingestDocument(MultipartFile file) ;
}
