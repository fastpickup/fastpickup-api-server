package com.project.fastpickup.admin.member.exception;

/*
 * Date   : 2023.08.14
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");

        if (authException instanceof BadCredentialsException) {
            // 비밀번호 또는 사용자 이름이 잘못된 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed: Invalid credentials\"}");
        } else if (authException instanceof AccountExpiredException) {
            // 계정이 만료된 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed: Account expired\"}");
        } else if (authException instanceof DisabledException) {
            // 계정이 비활성화된 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed: Account disabled\"}");
        } else if (authException instanceof LockedException) {
            // 계정이 잠긴 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed: Account locked\"}");
        } else if (authException instanceof AuthenticationException) {
            // 404 에러 (요청한 핸들러가 없는 경우)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            response.getWriter().write("{\"error\": \"Resource not found\"}");
        } else if (authException instanceof CredentialsExpiredException) {
            // 자격증명(비밀번호)이 만료된 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed: Credentials expired\"}");
        } else {
            // 그 외의 다른 인증 예외
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("{\"error\": \"Authentication failed\"}");
        }
    }
}
