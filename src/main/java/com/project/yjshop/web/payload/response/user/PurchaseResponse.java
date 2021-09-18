package com.project.yjshop.web.payload.response.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PurchaseResponse {
    private String message;
    private String product;
    private Long count;
    private Long price;
}
