package com.project.yjshop.web.payload.request.auth;

import lombok.Data;

@Data
public class JoinRequest {

    private String email;

    private String userid;

    private String password;

    private String nickname;
}
