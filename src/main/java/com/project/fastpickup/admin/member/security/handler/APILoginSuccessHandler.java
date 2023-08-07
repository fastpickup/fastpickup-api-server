package com.project.fastpickup.admin.member.security.handler;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.google.gson.Gson;
import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.util.JWTUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

// 인증 성공 시
@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        log.info("==========================");
        log.info(authentication);
        log.info("==========================");

        // 로그인 성공 시 DTO로 뽑고 gson 으로 만들어서 claim 뒤 JWT토큰 만들어서 보낸다.
        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        log.info("MemberDTO===============================================================API SUCCESS HANDLER: " + memberDTO);
        log.info("MemberDTO===============================================================: " + memberDTO);
        log.info("MemberDTO===============================================================: " + memberDTO);
        log.info("MemberDTO===============================================================: " + memberDTO);
        log.info("MemberDTO===============================================================: " + memberDTO);
        Map<String, Object> claims = memberDTO.getClaims();
        log.info(claims);
        log.info(claims);
        log.info(claims);
        log.info(claims);
        log.info(claims);

        String accessToken = JWTUtil.generateToken(claims, 10);
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);
        String roleNames = authentication.getAuthorities().toString();
        String memberName = memberDTO.getMemberName();

        claims.put("roleNames", roleNames);
        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);
        claims.put("memberName", memberName);

        Gson gson = new Gson();

        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}
