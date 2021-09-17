package com.project.yjshop.web.payload.request.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String title;

    private MultipartFile titleImage;

    private Long price;
}
