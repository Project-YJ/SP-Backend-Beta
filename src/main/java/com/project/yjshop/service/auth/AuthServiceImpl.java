package com.project.yjshop.service.auth;

import com.project.yjshop.domain.token.RefreshToken;
import com.project.yjshop.domain.token.RefreshTokenRepository;
import com.project.yjshop.domain.user.User;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.domain.user.UserRole;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.jwt.JwtTokenProvider;
import com.project.yjshop.web.payload.request.auth.JoinRequest;
import com.project.yjshop.web.payload.request.auth.LoginRequest;
import com.project.yjshop.web.payload.response.auth.CustomResponse;
import com.project.yjshop.web.payload.response.auth.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh}")
    private Long ref_time;

    @Override
    @Transactional
    public CustomResponse<?> join(JoinRequest joinRequest, BindingResult binding) {

        if (binding.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : binding.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(ErrorCode.JOIN_FAILED, errorMap);
        } else {
            if (userRepository.existsByUseridOrNickname(joinRequest.getUserid(), joinRequest.getNickname())) {
                throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
            }

            userRepository.save(
                    User.builder()
                            .email(joinRequest.getEmail())
                            .userid(joinRequest.getUserid())
                            .password(passwordEncoder.encode(joinRequest.getPassword()))
                            .nickname(joinRequest.getNickname())
                            .money(0L)
                            .role(UserRole.USER)
                            .build());

            return CustomResponse.builder()
                    .key(userRepository.findByUserid(joinRequest.getUserid()).get().getId())
                    .message("회원가입 성공")
                    .build();
        }
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest loginRequest, BindingResult binding) {
        if (binding.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : binding.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(ErrorCode.LOGIN_FAILED, errorMap);
        } else {
            User userEntity = userRepository.findByUserid(loginRequest.getUserid())
                    .orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
            if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            }
            return tokenProvider.createToken(loginRequest.getUserid());
        }
    }

    @Override
    @Transactional
    public TokenResponse reissue(String token) {
        if (tokenProvider.validateToken(token) && tokenProvider.isRefresh(token)) {
            RefreshToken refreshToken = refreshTokenRepository.findByToken(token).get();
//            refreshToken.update(ref_time);
            return TokenResponse.builder()
                    .accessToken(tokenProvider.createAccess(refreshToken.getUsername()))
                    .refreshToken(token)
                    .build();
        }
        return null;
    }
}