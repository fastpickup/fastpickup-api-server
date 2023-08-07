package com.project.fastpickup.admin.member.config;

import java.util.Arrays;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.fastpickup.admin.member.security.filter.JWTCheckFilter;
import com.project.fastpickup.admin.member.security.handler.APILoginSuccessHandler;
import com.project.fastpickup.admin.member.security.handler.CustomAccessDeniedHandler;
import com.project.fastpickup.admin.member.security.handler.OAuthAPILoginSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;

    // TokenRepository에 토큰 값 저장 함수
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Seucirty Filter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Spring Seucirty Filter Chain Is Running");

        http.rememberMe(config -> {
            // 발행한 토큰 값 repository에 저장
            config.tokenRepository(persistentTokenRepository());
            config.tokenValiditySeconds(60 * 60 * 24 * 7); // 7 Days
        });

        // cors 설정
        http.cors(config -> config.configurationSource(corsConfigurationSource()));

        // scrf 사용안함
        http.csrf((config -> config.disable()));

        // 로그인 설정이지만 화면은 X POSTMAN으로 테스트
        // 로그인 후 successHandler작동
        http.formLogin(config -> {
            config.loginPage("/api/member/login");

            // POSTMAN 시 빈화면 => 사용자 정보들을 다 가지고온다.
            config.successHandler(new APILoginSuccessHandler());
        });

        // kakao login
        http.oauth2Client(config -> {

        });

        // Oatuh2 Login
        http.oauth2Login(config -> {
            // react login Successs Hnalder 안에서 refresh Token 발급
            log.info("--------------------------------------------------Oauth Login--------------------------------------");
            config.successHandler(new OAuthAPILoginSuccessHandler());
        });
        http.exceptionHandling(config -> config.accessDeniedHandler(new CustomAccessDeniedHandler()));

        // API서버 => 세션 쿠키 사용안함
        http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 로그인 전에 JWTCheckFilter 사용
        http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // CrossOrigin 사용 안해도 된다.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}