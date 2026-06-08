package com.vedaai.rag.service.extraction.extractor;
import org.springframework.web.multipart.MultipartFile;

public interface TextExtractor {

    String extract(MultipartFile file) throws Exception;

    boolean supports(String extension);
}