package com.project.yjshop.security.jwt;

import com.project.yjshop.error.exception.CustomException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
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
            JSONObject write = new JSONObject();
            write.put("message", e.getErrorCode().getMessage());
            write.put("error", e.getErrorMap());

            response.setContentType("application/json");
            response.setStatus(e.getErrorCode().getStatus());
            response.getWriter().write(write.toJSONString());
        }
    }
}
