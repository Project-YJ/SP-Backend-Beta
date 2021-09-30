package com.project.yjshop.web.payload.response.board;

import lombok.*;

@Builder
@Data
public class BoardProductResponse {

    private String message;

    private final Product product;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private Integer boardId;
        private String title;
        private String titleImage;
        private Integer price;
        private Integer count;
    }
}
