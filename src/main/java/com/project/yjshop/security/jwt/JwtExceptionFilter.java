package com.project.yjshop.security.jwt;

import com.project.yjshop.error.CMRespDto;
import com.project.yjshop.error.exception.CustomException;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            CMRespDto error = new CMRespDto(e.getErrorCode().getMessage());

            response.setContentType("application/json");
            response.setStatus(e.getErrorCode().getStatus());
            response.getWriter().write(error.toString());
        }
    }
}
