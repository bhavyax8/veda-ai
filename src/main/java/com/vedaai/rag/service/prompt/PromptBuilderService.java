package com.vedaai.rag.service.prompt;

import java.util.List;

public interface PromptBuilderService {

    String buildPrompt(
            String question,
            List<String> chunks
    );
}