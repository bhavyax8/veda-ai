package com.vedaai.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QueryRequest {

    @NotBlank(message = "Question cannot be empty")
    private String question;
}