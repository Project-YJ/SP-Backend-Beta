package com.project.yjshop.web.payload.request.user;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserProductRequest {

    @NotNull
    public Long boardPk;

    @NotNull
    @Min(value = 1, message = "최소개수는 1개입니다")
    public Long count;
}
