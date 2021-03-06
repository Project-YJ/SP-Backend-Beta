package com.project.yjshop.web.payload.request.user;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductRequest {

    @NotNull
    public Integer boardPk;

    @NotNull
    @Min(value = 1, message = "최소개수는 1개입니다")
    public Integer count;
}
