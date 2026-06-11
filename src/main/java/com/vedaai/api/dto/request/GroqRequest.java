package com.vedaai.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

import com.vedaai.api.dto.Message;

@Getter
@Builder
public class GroqRequest {

    private String model;

    private List<Message> messages;
}