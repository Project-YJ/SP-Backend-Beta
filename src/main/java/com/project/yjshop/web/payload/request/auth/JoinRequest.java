package com.project.yjshop.web.payload.request.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;

    @NotBlank(message = "아이디를 입력해주세요")
    @Size(min = 2, max = 20, message = "아이디는 최소 2글자, 최대 20글자의 아이디여야 합니다.")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp="(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9])(?=\\S+$).{8,}$",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자이상의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자, 최대 20글자의 닉네임이여야 합니다.")
    private String nickname;
}
