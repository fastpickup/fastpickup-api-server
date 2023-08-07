package com.project.fastpickup.admin.member.security;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.dto.MemberReadDTO;
import com.project.fastpickup.admin.member.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("userRequest....");
        log.info(userRequest);

        log.info("oauth2 user.....................................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME: " + clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        switch (clientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        log.info("===============================");
        log.info(email);
        log.info("===============================");

        MemberDTO memberDTO = generateDTO(email, paramMap);

        log.info("MEMBERDTO: " + memberDTO);

        return memberDTO;
    }

    private MemberDTO generateDTO(String email, Map<String, Object> params) {

        MemberReadDTO member = memberMapper.selectOne(email);

        // 데이터베이스에 해당 이메일을 사용자가 없다면
        if (member == null) {
            // 회원 추가 -- mid는 이메일 주소/ 패스워드는 1111
            MemberConvertDTO socialMember = MemberConvertDTO.builder()
                    .email(email)
                    .memberName("카카오사용자")
                    .memberPw(passwordEncoder.encode("1111"))
                    .build();
            String memberRole = "USER";
            memberMapper.createJoinMemberRole(email, memberRole);
            memberMapper.joinMember(socialMember);

            // MemberDTO 구성 및 반환
            MemberDTO memberDTO = new MemberDTO(email, "1111", "카카오사용자", List.of("USER"));
            memberDTO.setProps(params);

            return memberDTO;
        } else {

            MemberDTO memberDTO = new MemberDTO(
                    member.getEmail(),
                    member.getMemberPw(),
                    member.getMemberName(),
                    member.getRolenames());

            return memberDTO;
        }
    }

    private String getKakaoEmail(Map<String, Object> paramMap) {

        log.info("KAKAO-----------------------------------------");

        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String email = (String) accountMap.get("email");

        log.info("email..." + email);

        return email;
    }
}
