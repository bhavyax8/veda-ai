package com.vedaai.rag.service.extraction.factory;

import com.vedaai.rag.exception.ExtractionException;
import com.vedaai.rag.service.extraction.extractor.TextExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TextExtractorFactory {

    private final List<TextExtractor> extractors;

    public TextExtractor getExtractor(String extension) {

        return extractors.stream()
                .filter(extractor ->
                        extractor.supports(extension))
                .findFirst()
                .orElseThrow(() ->
                        new ExtractionException(
                                "Unsupported file type: "
                                        + extension
                        ));
    }
}