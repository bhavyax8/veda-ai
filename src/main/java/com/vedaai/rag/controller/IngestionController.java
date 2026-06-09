package com.vedaai.rag.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.vedaai.api.dto.ServerResponse;
import com.vedaai.rag.service.ingestion.DocumentIngestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rag")
@RequiredArgsConstructor
public class IngestionController {

    private final DocumentIngestionService ingestionService;

    @PostMapping("/ingest")
    public ResponseEntity<ServerResponse> ingestFile(
            @RequestParam("file") MultipartFile file)  {

        ingestionService.ingestDocument(file);

        ServerResponse response = new ServerResponse();

        response.setStatus(200);
        response.setMessage(
                "File ingested successfully");

        return ResponseEntity.ok(response);
    }

}
