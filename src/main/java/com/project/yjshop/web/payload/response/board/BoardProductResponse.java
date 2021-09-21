package com.project.yjshop.web.payload.response.board;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardProductResponse {

    private String message;

    private final Product product;

    @Builder
    @Data
    public static class Product {
        private Long boardId;
        private String title;
        private String titleImage;
        private Long price;
        private Long count;
    }
}
