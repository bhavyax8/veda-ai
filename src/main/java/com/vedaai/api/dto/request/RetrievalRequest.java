package com.vedaai.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrievalRequest {

    @NotBlank(message = "Query cannot be empty")
    private String query;
}