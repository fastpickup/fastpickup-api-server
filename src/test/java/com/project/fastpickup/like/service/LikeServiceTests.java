package com.project.fastpickup.like.service;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.like.dto.LikeCreateDTO;
import com.project.fastpickup.admin.like.dto.LikeDTO;
import com.project.fastpickup.admin.like.mappers.LikeMapper;
import com.project.fastpickup.admin.like.service.LikeService;

import lombok.extern.log4j.Log4j2;

// Like Service Test Class 
@Log4j2
@SpringBootTest
public class LikeServiceTests {

    // 의존성 주입
    @Autowired(required = false)
    private LikeMapper likeMapper;

    @Autowired
    private LikeService likeService;

    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_PROUDCT_NUMBER = 22L;

    // BeforeEach 사용을 위한 DTO 정의
    private LikeDTO likeDTO;
    private LikeCreateDTO likeCreateDTO;

    // BeforeEach
    @BeforeEach
    public void setUp() {
        likeCreateDTO = LikeCreateDTO.builder()
                .email(TEST_EMAIL)
                .pno(TEST_PROUDCT_NUMBER)
                .build();

        likeDTO = LikeDTO.builder()
                .email(TEST_EMAIL)
                .pno(TEST_PROUDCT_NUMBER)
                .build();
    }

    // Create And Delete Like Service Test
    @Test
    @Transactional
    @DisplayName("Service: 라이크 생성 & 삭제 테스트")
    public void createAndDeleteLikeService() {
        // GIVEN
        log.info("=== Start Create & Delete Like Service ===");
        // WHEN 
        likeService.toggleLike(TEST_PROUDCT_NUMBER, TEST_EMAIL);
        // THEN 
        Long count = likeService.countLike(TEST_PROUDCT_NUMBER);
        Assertions.assertEquals(TEST_PROUDCT_NUMBER, 22L);
        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertNotNull(count, "count Should Be 1");
        log.info("=== End Create & Delete Like Service ===");
    }

    // Count Like Service Test
    @Test
    @Transactional
    @DisplayName("Service: 라이크 개수 조회")
    public void countLikeService() {
        // GIVEN 
        log.info("=== Start Count Like Service ===");
        // WHEN 
        Long count = likeService.countLike(TEST_PROUDCT_NUMBER);
        // THEN 
        Assertions.assertEquals(count, 0L ,"count Should Be Null");
        log.info("=== End Count Like service ===");
    }
}
