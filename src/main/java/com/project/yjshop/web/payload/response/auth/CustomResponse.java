package com.project.yjshop.web.payload.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomResponse<T> {
    private String message;
    private T key;
}
