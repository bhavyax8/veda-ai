package com.vedaai.rag.service.retrieval;

import com.vedaai.api.dto.request.RetrievalRequest;
import com.vedaai.api.dto.response.RetrievalResponse;

public interface RetrievalService {

    RetrievalResponse retrieve(RetrievalRequest request);
}