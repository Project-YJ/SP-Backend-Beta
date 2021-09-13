package com.project.yjshop.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> {
    private String message;
    private T error;
}
