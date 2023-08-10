package com.project.fastpickup.admin.member.restcontroller;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.member.exception.CustomJWTException;
import com.project.fastpickup.admin.member.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {

   @RequestMapping(value = "/api/member/refresh", method = RequestMethod.POST)
public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, @RequestParam String refreshToken) {

        log.info("refreshToken: "+refreshToken);
        log.info("authHeader: "+authHeader);
        
        if (refreshToken == null) {
            throw new CustomJWTException("NULL_REFRASH");
        }

        if (authHeader == null || authHeader.length() < 7) {
            throw new CustomJWTException("INVALID_STRING");
        }

        String accessToken = authHeader.substring(7);
        log.info("베어러 제거한 엑세스 토큰: "+ accessToken);

        // Access 토큰이 만료되지 않았다면
        if (checkExpiredToken(accessToken) == false) {
            log.info("만료되지않음");
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }

        // Refresh토큰 검증
        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

        log.info("refresh ... claims: " + claims);

        String newAccessToken = JWTUtil.generateToken(claims, 10);

        String newRefreshToken = checkTime((Integer) claims.get("exp")) == true ? JWTUtil.generateToken(claims, 60 * 24)
                : refreshToken;

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);

    }

    // 시간이 1시간 미만으로 남았다면
    private boolean checkTime(Integer exp) {
        log.info("엑세스 한시간 미만");
        // JWT exp를 날짜로 변환
        java.util.Date expDate = new java.util.Date((long) exp * (1000));

        // 현재 시간과의 차이 계산 - 밀리세컨즈
        long gap = expDate.getTime() - System.currentTimeMillis();

        // 분단위 계산
        long leftMin = gap / (1000 * 60);

        // 1시간도 안남았는지..
        return leftMin < 60;
    }

    private boolean checkExpiredToken(String token) {

        try {
            JWTUtil.validateToken(token);
        } catch (CustomJWTException ex) {
            if (ex.getMessage().equals("Expired")) {
                return true;
            }
        }
        return false;
    }

}