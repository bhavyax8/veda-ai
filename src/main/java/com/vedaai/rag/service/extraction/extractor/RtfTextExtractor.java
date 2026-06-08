package com.vedaai.rag.service.extraction.extractor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

@Component
public class RtfTextExtractor implements TextExtractor {

    @Override
    public String extract(MultipartFile file) throws Exception {

        RTFEditorKit rtfEditorKit =
                new RTFEditorKit();

        Document document =
                rtfEditorKit.createDefaultDocument();

        rtfEditorKit.read(
                file.getInputStream(),
                document,
                0
        );

        return document.getText(
                0,
                document.getLength()
        );
    }

    @Override
    public boolean supports(String extension) {
        return extension.equals("rtf");
    }
}