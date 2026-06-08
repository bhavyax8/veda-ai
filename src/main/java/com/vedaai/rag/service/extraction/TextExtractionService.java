package com.vedaai.rag.service.extraction;

import org.springframework.web.multipart.MultipartFile;

public interface TextExtractionService  {
     String extractText(MultipartFile file);
}
