package com.project.fastpickup.like.mappers;

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

import lombok.extern.log4j.Log4j2;

// Like Mapper Test Class
@Log4j2
@SpringBootTest
public class LikeMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private LikeMapper likeMapper;

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

    // Create Like Mapper Test
    @Test
    @Transactional
    @DisplayName("Mapper: 상품 좋아요 생성 테스트")
    public void createLikeMapper() {
        // GIVEN
        log.info("=== Start Create Like Mapper Test ===");
        // WHEN
        likeMapper.createLike(likeCreateDTO);
        likeMapper.incrementLikeCount(likeCreateDTO.getPno());
        // THEN
        Long count = likeMapper.countLikes(TEST_PROUDCT_NUMBER);
        Assertions.assertNotNull(count, "count Should Be Not Null");
        log.info("=== End Create Like Mapper Test ===");
    }

    // Delete Like Mapper Test
    @Test
    @Transactional
    @DisplayName("Mapper: 상품 좋아요 삭제 테스트")
    public void deleteLikeMapper() {
        // GIVEN
        log.info("=== Start Delete Like Mapper Test ===");
        // WHEN
        likeMapper.deleteLike(likeDTO);
        likeMapper.decrementLikeCount(likeCreateDTO.getPno());
        // THEN
        Long count = likeMapper.countLikes(TEST_PROUDCT_NUMBER);
        Assertions.assertEquals(0L, count, "Count Should Be Zero");
        log.info("=== End Delete Like Mapper Test ===");
    }

    // Count Like Mapper Test
    @Test
    @Transactional
    @DisplayName("Mapper: 좋아요 조회 테스트")
    public void countLikeMapper() {
        // GIVEN
        log.info("=== Start Count Like Mapper Test ===");
        // WHEN
        Long count = likeMapper.countLikes(TEST_PROUDCT_NUMBER);
        // THEN
        log.info(count);
        log.info("=== End Count Like Mapper Test ===");
    }

    // Check Like Mapper Test
    @Test
    @Transactional
    @DisplayName("Mapper: 좋아요 생성과 삭제 전 조회 테스트")
    public void readLikeBeforeCreateAndDelete() {
        // GIVEN
        log.info("=== Start Read Like Mapper Test ===");
        // WHEN
        LikeDTO count = likeMapper.checkLikeByMemberAndPost(TEST_PROUDCT_NUMBER, TEST_EMAIL);
        // THEN
        Assertions.assertNull(count, "count Should Be Null");
        log.info("=== Start Read Like Mapper Test ===");
    }
}
