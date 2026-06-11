package com.vedaai.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryResponse {

    private String question;

    private String answer;

    private List<SourceInfo> sources;
}