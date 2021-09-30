package com.project.yjshop.web.payload.request.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;

    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 2, max = 20, message = "아이디는 최소 2글자, 최대 20글자의 아이디여야 합니다.")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
