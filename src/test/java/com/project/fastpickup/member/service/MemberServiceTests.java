package com.project.fastpickup.member.service;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class MemberServiceTests {

    // 의존성 주입
    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_EMAIL_VERSION_2 = "twefiwefew@naver.com";
    private static final String TEST_EMAIL_VERSION_3 = "wfewfewfew@naver.com";
    private static final String TEST_EMAIL_VERSION_4 = "wfewfiewfewe@naver.com";
    private static final String TEST_MEMBER_PW = "1111";
    private static final String TEST_MEMBER_NAME = "권성준";
    private static final String TEST_MEMBER_PHONE = "010-3099-0648";
    private static final String TEST_STORE = "s";

    // DTO 정의
    private MemberConvertDTO memberConvertDTO;
    private MemberConvertDTO updateMemberConvertDTO;
    private MemberConvertDTO joinStoreMemberDTO;
    private MemberConvertDTO updateStoreMeberDTO;

    // BeforeEach 사용을 위한 setUp
    @BeforeEach
    public void setUp() {
        // 회원가입
        memberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL_VERSION_2)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .build();

        // 회원 업데이트
        updateMemberConvertDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .build();

        // 가맹점 가입
        joinStoreMemberDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL_VERSION_4)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .store(TEST_STORE)
                .build();

        // 가맹점 업데이트
        updateStoreMeberDTO = MemberConvertDTO.builder()
                .email(TEST_EMAIL_VERSION_3)
                .memberPw(passwordEncoder.encode(TEST_MEMBER_PW))
                .memberName(TEST_MEMBER_NAME)
                .memberPhone(TEST_MEMBER_PHONE)
                .store(TEST_STORE)
                .build();
    }

    // Join Member Test Service     
    @Test
    @Transactional
    @DisplayName("멤버 생성 테스트")
    public void joinMemberTestService() {
        // GIVEN 
        log.info("=== Start Join Member Test Service ===");
        // WHEN 
        memberService.joinMember(memberConvertDTO);
        // THEN 
        Assertions.assertEquals(memberConvertDTO.getEmail(), "twefiwefew@naver.com");
        log.info("=== End Join Member Test Service ===");
    }

    // Update Member Test Service
    @Test
    @Transactional
    @DisplayName("멤버 업데이트 테스트")
    public void updateMemberTestService() {
        // GIVEN 
        log.info("=== Start Update Member Test Service ===");
        // WHEN 
        memberService.updateMember(updateMemberConvertDTO);
        // THEN 
        MemberConvertDTO updatedMember = memberService.readMember(TEST_EMAIL);
        Assertions.assertNotNull(updatedMember, "updatedMember Should Be Not Null");
        Assertions.assertEquals(updateMemberConvertDTO.getEmail(), "thistrik@naver.com");
        Assertions.assertEquals(updateMemberConvertDTO.getMemberName(), "권성준");
        Assertions.assertEquals(updateMemberConvertDTO.getMemberPhone(), "010-3099-0648");
        log.info("=== End Update Member Test Service ===");
    }

    // Read Store & Member Test Service
    @Test
    @Transactional
    @DisplayName("회원 상세조회 테스트")
    public void readMemberTestService() {
        // GIVEN 
        log.info("=== Start Read Member Test Service ===");
        // WHEN 
        MemberConvertDTO readMember = memberService.readMember(TEST_EMAIL);
        // THEN 
        log.info(readMember);
        Assertions.assertNotNull(readMember, "readMember Should Be Not Null");
        Assertions.assertEquals(readMember.getEmail(), "thistrik@naver.com");
        Assertions.assertEquals(readMember.getMemberName(), "권성준");
        log.info("=== End Read Member Test Service ===");
    }

    // Delete Store & Member Test Service
    @Test
    @Transactional
    @DisplayName("회원 탈퇴 테스트")
    public void deleteMemberTestService() {
        // GIVEN 
        log.info("=== Start Delete Member Test Service ===");
        // WHEN 
        memberService.deleteMember(TEST_EMAIL);
        // THEN 
        MemberConvertDTO deletedMember = memberService.readMember(TEST_EMAIL);
        Assertions.assertNull(deletedMember, "deletedMember Should Be Null");
        log.info("=== End Delete Member Test Service ===");
    }
}
