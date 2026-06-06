package com.vedaai.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final DataSource dataSource;

    @Value("${spring.ai.openai.base-url}")
    private String groqUrl;

    @Value("${spring.ai.ollama.base-url}")
    private String ollamaUrl;

    public HealthController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/health")
    public Map<String, String> health() {

        Map<String, String> response = new HashMap<>();

        response.put("status", "ok");

        try (Connection connection = dataSource.getConnection()) {
            response.put("database", "connected");
        } catch (Exception e) {
            response.put("database", "disconnected");
        }

        response.put("llm", groqUrl != null ? "groq-connected" : "groq-missing");

        response.put("embedding", ollamaUrl != null
                ? "ollama-connected"
                : "ollama-missing");

        return response;
    }
}