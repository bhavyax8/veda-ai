package com.vedaai.rag.service.extraction.extractor;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DocxTextExtractor implements TextExtractor {

    @Override
    public String extract(MultipartFile file) throws Exception {

        try (XWPFDocument document = new XWPFDocument(file.getInputStream());
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        }
    }

    @Override
    public boolean supports(String extension) {
        return extension.equals("docx");
    }
}