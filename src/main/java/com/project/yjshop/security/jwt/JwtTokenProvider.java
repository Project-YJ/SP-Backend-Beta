package com.project.yjshop.security.jwt;

import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetailsService;
import com.project.yjshop.web.payload.response.auth.TokenDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${jwt.header}")
    private String header;

    public TokenDto createToken(String username) {

        Date now = new Date();
        String accessToken =Jwts.builder()
                .setClaims(Jwts.claims().setSubject(username))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(1).toMillis()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + Duration.ofDays(1).toMillis()))
                .setClaims(Jwts.claims().setSubject(secretKey))
                .compact();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        UserDetails userDetails = principalDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().getExpiration().after(new Date());
        } catch (SecurityException | MalformedJwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRATION_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new CustomException(ErrorCode.NOT_SUPPORTED_TOKEN);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.hasText(token) && token.startsWith(prefix)) {
            return token.substring(prefix.length());
        }
       return null;
    }
}
