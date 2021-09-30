package com.project.yjshop.web.payload.response.board;

import lombok.*;

import java.util.List;

@Builder
@Data
public class CategoryResponse {

    private List<listCategory> categories;

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class listCategory {
        private String name;
        private Integer count;
    }
}
