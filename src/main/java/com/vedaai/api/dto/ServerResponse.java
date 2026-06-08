package com.vedaai.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse {
    private Integer status;
    private String message;
    private Object data;
    private String error;
}
