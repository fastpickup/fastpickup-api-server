package com.project.fastpickup.admin.member.security;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.member.dto.MemberDTO;
import com.project.fastpickup.admin.member.dto.MemberReadDTO;
import com.project.fastpickup.admin.member.mappers.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    //
    private final MemberMapper memberMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /* 리턴타입이 UserDetails 이므로 Mapper를 통해 MemberDTO로 반환해야한다. */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUserName =====================================================: " + username);



        MemberReadDTO readDTO = memberMapper.selectOne(username);

        log.info("readDTO===========================================================:" + readDTO);

        MemberDTO memberDTO = new MemberDTO(
                username,
                readDTO.getMemberPw(),
                readDTO.getMemberName(),
                // 권한은 SimpleGranteAuthority객체이므로 나중에 Map을 써서 타입을 바꿔줘야 함
                readDTO.getRolenames());

        log.info(memberDTO);
        log.info(memberDTO);log.info(memberDTO);log.info(memberDTO);log.info(memberDTO);
        log.info(memberDTO);log.info(memberDTO);
        log.info(memberDTO);
        log.info(memberDTO);
        log.info(memberDTO);





        return memberDTO;
    }
}