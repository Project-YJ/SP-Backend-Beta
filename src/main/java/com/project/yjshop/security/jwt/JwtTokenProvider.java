package com.project.yjshop.security.jwt;

import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.web.payload.response.auth.TokenDto;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${jwt.header}")
    private String header;

    public String init() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDto createToken(String username) {

        Date now = new Date();

        String accessToken =Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(Jwts.claims().setSubject(username))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(1).toMillis()))
                .signWith(SignatureAlgorithm.HS256, init())
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + Duration.ofDays(1).toMillis()))
                .signWith(SignatureAlgorithm.HS256, init())
                .compact();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
