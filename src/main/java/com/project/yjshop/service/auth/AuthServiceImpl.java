package com.project.yjshop.service.auth;

import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.domain.user.UserRole;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.response.auth.JoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public JoinResponse join(JoinRequest joinRequest, BindingResult binding) {

        if(binding.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: binding.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(ErrorCode.JOIN_FAILED, errorMap);
        } else {
            if(userRepository.existsByUseridOrNickname(joinRequest.getUserid(), joinRequest.getNickname())) {
                throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
            }

            userRepository.save(
                    User.builder()
                            .email(joinRequest.getEmail())
                            .userid(joinRequest.getUserid())
                            .password(passwordEncoder.encode(joinRequest.getPassword()))
                            .nickname(joinRequest.getNickname())
                            .role(UserRole.USER)
                            .build());

            return JoinResponse.builder()
                    .pk(userRepository.findByUserid(joinRequest.getUserid()).getId())
                    .message("회원가입 성공")
                    .build();
        }
    }
}