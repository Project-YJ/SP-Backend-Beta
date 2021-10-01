package com.project.yjshop.web.payload.response.board;

import lombok.*;

import java.util.List;

@Builder
@Data
public class BoardCategoryResponse {

    private List<BoardCategoryResponse.product> productList;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class product {
        private String title;
        private String titleImage;
        private Integer price;
        private Integer count;
    }
}
