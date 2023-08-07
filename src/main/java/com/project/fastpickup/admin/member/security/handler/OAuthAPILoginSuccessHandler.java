package com.project.fastpickup.admin.member.security.handler;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;
import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.util.JWTUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OAuthAPILoginSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    log.info("OAuth onAuthenticationSuccess.................");

    String email = authentication.getName();

    log.info("SEND TO APPLICATION: " + email);

    MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();

    Map<String, Object> claims = memberDTO.getClaims();

    String accessToken = JWTUtil.generateToken(claims, 10);

    String refreshToken = JWTUtil.generateToken(claims, 60 * 24);

    String memberName = memberDTO.getMemberName();

    Gson gson = new Gson();

    // Map<String, Object> dataMap = new HashMap<>();

    // dataMap.putAll(claims);

    claims.put("accessToken", accessToken);
    claims.put("refreshToken", refreshToken);
    claims.put("memberName", memberName);

    String jsonStr = gson.toJson(claims);

    String encodeStr = URLEncoder.encode(jsonStr, "UTF-8");

    response.sendRedirect("http://localhost:3000/member/mypage?data=" + encodeStr);

  }

}
