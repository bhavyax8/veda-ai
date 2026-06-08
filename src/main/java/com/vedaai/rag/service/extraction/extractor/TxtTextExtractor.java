package com.vedaai.rag.service.extraction.extractor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Component
public class TxtTextExtractor implements TextExtractor {

    @Override
    public String extract(MultipartFile file) throws Exception {

        return new String(
                file.getBytes(),
                StandardCharsets.UTF_8
        );
    }

    @Override
    public boolean supports(String extension) {
        return extension.equals("txt");
    }
}