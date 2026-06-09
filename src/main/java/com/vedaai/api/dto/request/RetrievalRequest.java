package com.vedaai.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RetrievalRequest {

    @NotBlank(message = "Query cannot be empty")
    private String query;
}