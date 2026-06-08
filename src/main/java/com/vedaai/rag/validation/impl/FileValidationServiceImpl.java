package com.vedaai.rag.validation.impl;

import com.vedaai.rag.exception.FileValidationException;
import com.vedaai.rag.exception.UnsupportedFileTypeException;
import com.vedaai.rag.validation.FileValidationService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
public class FileValidationServiceImpl
        implements FileValidationService {

    private static final long MAX_FILE_SIZE =
            10 * 1024 * 1024;

    private static final Set<String>
            SUPPORTED_EXTENSIONS = Set.of(
            "pdf",
            "docx",
            "txt",
            "rtf",
            "odt"
    );

    @Override
    public void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {

            throw new FileValidationException(
                    "File is empty"
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {

            throw new FileValidationException(
                    "File size exceeds limit"
            );
        }

        String filename =
                file.getOriginalFilename();

        if (filename == null ||
                !filename.contains(".")) {

            throw new FileValidationException(
                    "Invalid filename"
            );
        }

        String extension =
                filename.substring(
                        filename.lastIndexOf(".") + 1
                ).toLowerCase();

        if (!SUPPORTED_EXTENSIONS
                .contains(extension)) {

            throw new UnsupportedFileTypeException(
                    "Unsupported file type"
            );
        }
    }
}