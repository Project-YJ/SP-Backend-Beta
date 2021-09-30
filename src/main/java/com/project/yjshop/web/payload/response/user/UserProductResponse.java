package com.project.yjshop.web.payload.response.user;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductResponse {
    private String message;
    private String product;
    private Long count;
    private Long price;
}
