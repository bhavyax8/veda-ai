package com.vedaai.rag.controller;

import com.vedaai.api.dto.request.RetrievalRequest;
import com.vedaai.api.dto.response.RetrievalResponse;
import com.vedaai.rag.service.retrieval.RetrievalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rag")
@RequiredArgsConstructor
public class RetrievalController {

    private final RetrievalService retrievalService;

    @PostMapping("/search")
    public RetrievalResponse search(
            @Valid @RequestBody
            RetrievalRequest request
    ) {

        return retrievalService.retrieve(
                request
        );
    }
}