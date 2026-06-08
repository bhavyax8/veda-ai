package com.vedaai.rag.validation;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidationService {
        void validate(MultipartFile file);
}
