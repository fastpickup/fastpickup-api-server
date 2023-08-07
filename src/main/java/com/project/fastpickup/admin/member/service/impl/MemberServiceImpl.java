package com.project.fastpickup.admin.member.service.impl;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.exception.InvalidEmailException;
import com.project.fastpickup.admin.member.exception.UserEmailAlreadyExistsException;
import com.project.fastpickup.admin.member.exception.UserNotFoundException;
import com.project.fastpickup.admin.member.mappers.MemberMapper;
import com.project.fastpickup.admin.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

// Member ServiceImpl Class
@Log4j2
@Service
public class MemberServiceImpl implements MemberService {

    // 의존성 주입
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    // Autowired 명시적 표시
    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        log.info("Constructor Called, Mapper Injected.");
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Join Member ServiceImpl
    @Override
    @Transactional
    public int joinMember(MemberConvertDTO memberConvertDTO) {
        log.info("Is Running JoinMember ServiceImpl");
        // Check email format
        String email = memberConvertDTO.getEmail();
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InvalidEmailException("이메일 형식이 올바르지 않습니다: " + email);
        }
        String rolename = "USER";
        String encodedPassword = passwordEncoder.encode(memberConvertDTO.getMemberPw());
        memberConvertDTO.setMemberPw(encodedPassword);
        memberMapper.createJoinMemberRole(memberConvertDTO.getEmail(), rolename);
        return memberMapper.joinMember(memberConvertDTO);
    }

    // Delete Member ServiceImpl
    @Override
    @Transactional
    public int deleteMember(String email) {
        log.info("Is Running DeleteMember ServiceImpl");
        memberMapper.deleteMemberRole(email);
        return memberMapper.deleteMember(email);
    }

    // Update Member ServiceImpl
    @Override
    @Transactional
    public int updateMember(MemberConvertDTO memberConvertDTO) {
        log.info("Is Running UpdateMember ServiceImpl");
        return memberMapper.updateMember(memberConvertDTO);
    }

    // Read Meber ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public MemberConvertDTO readMember(String email) {
        log.info("Is Running ReadMember ServiceImpl");
        return memberMapper.readMember(email);
    }

    // Check Email Already Exists ServiceImpl
    @Override
    @Transactional
    public void checkEmailAlreadyExists(String email) {
        log.info("Is Running UserEmailAlreadyExistsException ServiceImpl");
        if (memberMapper.checkEmailForException(email) == 1) {
            throw new UserEmailAlreadyExistsException("이미 사용중인 이메일입니다.");
        }
    }

    // Search User ServiceImpl
    @Override
    @Transactional
    public void searchUser(String email) {
        log.info("Is Running UserNotFoundException ServiceImpl");
        if (memberMapper.checkEmailForException(email) == 0) {
            throw new UserNotFoundException("해당 유저가 없습니다.");
        }
    }
}
