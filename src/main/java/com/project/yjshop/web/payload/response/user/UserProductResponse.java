package com.project.yjshop.web.payload.response.user;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductResponse {
    private String message;
    private String product;
    private Integer count;
    private Integer price;
}
