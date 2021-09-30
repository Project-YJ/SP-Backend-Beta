package com.project.yjshop.web.payload.response.auth;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private String message;
    private T key;
}
