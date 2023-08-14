package com.project.fastpickup.admin.member.security.filter;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {

    // Should Not Filter 로 한번 Filtering 작업 검증
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        log.info("request.getMethod"+request.getMethod());
        // PreFlight
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        // 호출 하는 경로를 Check
        String path = request.getRequestURI();
        log.info("path: "+path);

        // Login 이면 Filter 검증 안한다
        if (path.equals("/api/member/login")
                || path.equals("/api/member/refresh")
        ) {
            return true;
        }

        if(path.startsWith("/api/member/refresh")) {
            return true;
        }

        if(path.equals("/api/member/create")) {
            return true;
        }

        if(path.startsWith("/kakaoPay")) {
            return true;
        }

        if (path.startsWith("/swagger-ui/")) {
            return true;
        }

        if(path.startsWith("/joon")) {
            return true;
        }
        

        // http://localhost:8081/login/oauth2/kakao
        // kakao login true
        if (path.startsWith("/login")) {
            return true;
        }

        if (path.startsWith("/oauth2")) {
            return true;
        }

        if (path.startsWith(".ico")) {
            return true;
        }

        if(path.equals("/api/product/category")){
            return true;
        }

        if(path.startsWith("/api/map/getKakaoApiKey")) {
            return true;
        }

        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("request JWT Filter: " + request );
        log.info("response JWT Filter: " +response);
        log.info("=========doFilterInternal=========");

        log.info("========doFilterInternal==========");

        String authHeaderStr = request.getHeader("Authorization");
        log.info("authHeaderStr JWT Filter: "+ authHeaderStr);
        try {
            // Bearer accestoken...
            String accessToken = authHeaderStr.substring(7);
            log.info("베어러 자른 엑세스:"+ accessToken);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);
            log.info("accessToken JWT Filter: "+accessToken);
            log.info("JWT claims: " + claims);

            String email = (String) claims.get("email");
            log.info("Email-----------------------: " + email);
            String memberPw = (String) claims.get("memberPw");
            //String memberPw = "1111";
            log.info("memberPw-----------------------: " + memberPw);
            String memberName = (String) claims.get("memberName");
            log.info("memberName-----------------------: " + memberName);

            List<String> roleNames = (List<String>) claims.get("roleNames");
            log.info("roleNames-----------------------: " + roleNames);

            MemberDTO memberDTO = new MemberDTO(email, memberPw, memberName, roleNames);
            log.info("memberDTO-----------------------: " + memberDTO);

            log.info("-----------------------------------");
            log.info(memberDTO);
            log.info(memberDTO.getAuthorities()+"인증정보");

            // 사용자의 정보를 가져와 토큰에 넣어서 시큐리티에서 쓸수있게 전달
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO,
                    memberPw, memberDTO.getAuthorities());
                    log.info("authenticationToken JWT Filter: "+authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

            // Access Token 문제 Exception
        } catch (Exception e) {

            log.error("JWT Check Error..............", e);
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            log.info("제이슨 메시지:"+ msg);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            log.info("프린트 라이터:"+ printWriter);
            printWriter.println(msg);
            printWriter.close();
        }
    }
}