package com.vedaai.rag.service.extraction.extractor;

import org.odftoolkit.simple.TextDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class OdtTextExtractor implements TextExtractor {

    @Override
    public String extract(MultipartFile file) throws Exception {

        TextDocument document =
                TextDocument.loadDocument(
                        file.getInputStream()
                );

        return document.getContentRoot()
                .getTextContent();
    }

    @Override
    public boolean supports(String extension) {
        return extension.equals("odt");
    }
}