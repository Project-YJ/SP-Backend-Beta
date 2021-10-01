package com.project.yjshop.web.payload.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCategoryRequest {
    private Integer categoryId;
}
