package com.vedaai.rag.controller;

import com.vedaai.api.dto.request.QueryRequest;
import com.vedaai.api.dto.response.QueryResponse;
import com.vedaai.rag.service.query.QueryService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
@RequiredArgsConstructor
public class QueryController {

    private final QueryService queryService;

    @PostMapping("/query")
    public QueryResponse query(
            @Valid
            @RequestBody
            QueryRequest request
    ) {

        return queryService.query(request);
    }
}