package com.vedaai.rag.service.extraction.impl;

import com.vedaai.rag.exception.ExtractionException;
import com.vedaai.rag.service.extraction.TextExtractionService;
import com.vedaai.rag.service.extraction.extractor.TextExtractor;
import com.vedaai.rag.service.extraction.factory.TextExtractorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TextExtractionServiceImpl
        implements TextExtractionService {

    private final TextExtractorFactory factory;

    @Override
    public String extractText(MultipartFile file) {

        try {

            String filename =
                    file.getOriginalFilename();

            if (filename == null ||
                    !filename.contains(".")) {

                throw new ExtractionException(
                        "Invalid filename"
                );
            }

            String extension =
                    filename.substring(
                            filename.lastIndexOf(".") + 1
                    ).toLowerCase();

            TextExtractor extractor =
                    factory.getExtractor(extension);

            return extractor.extract(file);

        } catch (Exception e) {

            throw new ExtractionException(
                    "Text extraction failed: "
                            + e.getMessage()
            );
        }
    }
}