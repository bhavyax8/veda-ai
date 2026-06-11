package com.vedaai.rag.service.query;

import com.vedaai.api.dto.request.QueryRequest;
import com.vedaai.api.dto.response.QueryResponse;

public interface QueryService {

    QueryResponse query(QueryRequest request);
}