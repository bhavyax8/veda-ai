package com.vedaai.rag.service.prompt.impl;

import com.vedaai.rag.service.prompt.PromptBuilderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptBuilderServiceImpl
        implements PromptBuilderService {

    @Override
    public String buildPrompt(
            String question,
            List<String> chunks
    ) {

        StringBuilder contextBuilder =
                new StringBuilder();

        for (int i = 0; i < chunks.size(); i++) {

            contextBuilder.append("""
                    
                    SOURCE %d:
                    %s
                    """.formatted(
                    i + 1,
                    chunks.get(i)
            ));
        }

        return """
                You are a helpful AI assistant.

                Answer the question ONLY using
                the provided context sources.

                If the answer exists in the context,
                provide a concise answer.

                Answer should not be generated if it does not exist in the context.


                CONTEXT:
                %s

                QUESTION:
                %s

                ANSWER:
                """.formatted(
                contextBuilder,
                question
        );
    }
}