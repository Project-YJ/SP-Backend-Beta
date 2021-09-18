package com.project.yjshop.web.payload.request.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductRequest {

    @NotBlank(message = "제목을 입력해주세요")
    @Size(min = 4, max = 100, message = "상품제목은 4~100글자 사이여야합니다.")
    private String title;

    @NotNull(message = "제품 수량을 입력해주세요")
    @Min(value = 1, message = "상품은 최소 1개여야합니다.")
    private Long count;

    @NotNull(message = "상품 이미지를 선택해주세요")
    private MultipartFile titleImage;

    @NotNull(message = "가격을 입력해주세요")
    @Min(value = 1000, message = "상품의 최소가격은 1000원입니다.")
    private Long price;
}
