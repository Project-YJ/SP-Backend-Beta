package com.project.yjshop.security.jwt;

import com.project.yjshop.domain.token.RefreshToken;
import com.project.yjshop.domain.token.RefreshTokenRepository;
import com.project.yjshop.domain.user.UserRepository;
import com.project.yjshop.error.ErrorCode;
import com.project.yjshop.error.exception.CustomException;
import com.project.yjshop.security.auth.PrincipalDetailsService;
import com.project.yjshop.web.payload.response.auth.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PrincipalDetailsService principalDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.access}")
    private Long acc_time;

    @Value("${jwt.refresh}")
    private Long ref_time;

    public boolean isRefresh(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("type").equals("refresh");
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_REFRESH);
        }
    }

    public String createAccess(String username) {
        Date now = new Date();
        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(username))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + acc_time))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public TokenResponse createToken(String username) {

        Date now = new Date();
        String accessToken = createAccess(username);

        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now.getTime() + ref_time))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .claim("type", "refresh")
                .compact();

        refreshTokenRepository.save(RefreshToken.builder()
                .token(refreshToken)
                .username(username)
                .delTime(ref_time/1000)
                .build());

        return TokenResponse.builder()
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
