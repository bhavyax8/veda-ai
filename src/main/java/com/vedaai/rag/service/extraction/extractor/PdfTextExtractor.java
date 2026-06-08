package com.vedaai.rag.service.extraction.extractor;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfTextExtractor implements TextExtractor {

    @Override
    public String extract(MultipartFile file) throws Exception {

        try (PDDocument document =
                     Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }

    @Override
    public boolean supports(String extension) {
        return extension.equals("pdf");
    }
}