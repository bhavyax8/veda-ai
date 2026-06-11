package com.vedaai.rag.service.groq.impl;

import com.vedaai.api.dto.*;
import com.vedaai.api.dto.request.GroqRequest;
import com.vedaai.rag.service.groq.GroqService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroqServiceImpl
        implements GroqService {

    private final RestTemplate restTemplate;

    @Value("${GROQ_API_URL}")
    private String groqApiUrl;

    @Value("${GROQ_API_KEY}")
    private String groqApiKey;

    @Override
    public String generateAnswer(String prompt) {

        GroqRequest request =
                GroqRequest.builder()
                        .model("llama-3.3-70b-versatile")
                        .messages(
                                List.of(
                                        Message.builder()
                                                .role("user")
                                                .content(prompt)
                                                .build()
                                )
                        )
                        .build();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        headers.setBearerAuth(groqApiKey);

        HttpEntity<GroqRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(
                        groqApiUrl,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

        List<Map<String, Object>> choices =
                (List<Map<String, Object>>)
                        response.getBody().get("choices");

        Map<String, Object> message =
                (Map<String, Object>)
                        choices.get(0).get("message");

        return message.get("content").toString();
    }
}