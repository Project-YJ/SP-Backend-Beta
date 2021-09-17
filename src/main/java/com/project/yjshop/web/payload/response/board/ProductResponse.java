package com.project.yjshop.web.payload.response.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class ProductResponse {

    private String message;

    private final Product product;

    @Builder
    @AllArgsConstructor @NoArgsConstructor
    public class Product {
        private Long boardId;
        private String title;
        private String titleImage;
        private Long price;
    }
}
