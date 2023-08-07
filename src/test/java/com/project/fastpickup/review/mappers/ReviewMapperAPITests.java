package com.project.fastpickup.review.mappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.dto.ReviewUpdateDTO;
import com.project.fastpickup.admin.review.mappers.ReviewFileMapper;
import com.project.fastpickup.admin.review.mappers.ReviewMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReviewMapperAPITests {

    @Autowired(required = false)
    private ReviewMapper reviewMapper;

    @Autowired(required = false)
    private ReviewFileMapper reviewFileMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final String TEST_EMAIL = "wndyd0110@naver.com";
    private static final Long TEST_REVIEW_RNO = 83L;
    private static final Long TEST_STORE_SNO = 1L;
    private static final String TEST_REVIEW_TITLE = "JUNIT Review Title Test222";
    private static final String TEST_REVIEW_CONTENT = "JUNIT Review CONTENT Test222";
    private static final Long TEST_ORDER_ONO = 22L;
    private static final String TEST_FILE_NAME = "Junit222.jpg"; // 파일명

    // DTO 정의
    private PageRequestDTO pageRequestDTO;
    private ReviewRegistDTO reviewRegistDTO;
    private ReviewUpdateDTO reviewUpdateDTO;

    @BeforeEach
    public void init() {

        // 페이징
        pageRequestDTO = PageRequestDTO.builder().build();

        // RegistReview DTO Builder
        reviewRegistDTO = ReviewRegistDTO.builder()
                .sno(TEST_STORE_SNO)
                .ono(TEST_ORDER_ONO)
                .email(TEST_EMAIL)
                .reviewTitle(TEST_REVIEW_TITLE)
                .reviewContent(TEST_REVIEW_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + TEST_FILE_NAME, UUID.randomUUID() + TEST_FILE_NAME))
                .build();

        // UpdateReveiw DTO Builder
        reviewUpdateDTO = ReviewUpdateDTO.builder()
                .rno(TEST_REVIEW_RNO)
                .reviewTitle(TEST_REVIEW_TITLE)
                .reviewContent(TEST_REVIEW_CONTENT)
                .fileNames(List.of(UUID.randomUUID() + TEST_FILE_NAME, UUID.randomUUID() + TEST_FILE_NAME))
                .build();
    }

    // ReviewMemberList Test
    @Test
    @Transactional
    @DisplayName("맴버 리뷰 리스트 조회 테스트")
    public void getReviewMemberList() {

        // GIVEN
        log.info("===Start ReviewMemberList Mapper Test");

        // WHEN
        List<ReviewListDTO> list = reviewMapper.getReviewMemberList(TEST_EMAIL, pageRequestDTO);

        // THEN
        log.info(list);
        Assertions.assertNotNull(list, "Reivew List is Null");
        log.info("=== End List Review Test Mapper ===");

    }

    // ReviewMemberList count Test
    // @Test
    // @Transactional
    // @DisplayName("멤버 리뷰 리스트 토탈")

    // ReadReview
    @Test
    @Transactional
    @DisplayName("맴버 리뷰 상세 조회 테스트")
    public void readReview() {

        // GIVEN
        log.info("===Start ReadReivew Mapper Test");

        // WHEN
        ReviewReadDTO readOne = reviewMapper.getReviewRead(TEST_REVIEW_RNO, TEST_EMAIL);

        // THEN
        log.info(readOne);
        Assertions.assertNotNull(readOne, "Reivew SelectOne is Null");
        log.info("=== End Read Review Test Mapper ===");
    }

    @Test
    @Transactional
    @DisplayName("리뷰 작성")
    public void registReview() {

        // GIVEN
        log.info("===Start RegistReview Mapper Test");

        // WHEN
        int insertCnt = reviewMapper.registReview(reviewRegistDTO);
        int updateGno = reviewMapper.updateGno(reviewRegistDTO.getRno());

        Long rno = reviewRegistDTO.getRno();
        log.info("등록한 리뷰 번호" + rno);

        List<String> fileNames = reviewRegistDTO.getFileNames();
        log.info(fileNames);

        // 상품 등록 성공과 파일이 등록되었다면 실행
        if (insertCnt > 0 && reviewRegistDTO.getFileNames() != null && !reviewRegistDTO.getFileNames().isEmpty()) {
            AtomicInteger index = new AtomicInteger();
            // 등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(list);
            // 파일 등록 실행
            reviewFileMapper.registReviewImg(list);
        }

        Assertions.assertEquals(1, insertCnt, "Review Register Test Fail");
        log.info("=== End Create Review Test Mapper ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 삭제 테스트")
    public void removeReivewTest() {

        // GIVEN
        log.info("===Start Remove Review Mapper Test");

        // WHEN
        int removeCount = reviewMapper.removeReview(TEST_REVIEW_RNO);
        int removeImg = reviewFileMapper.deleteReviewImg(TEST_REVIEW_RNO);

        // THEN
        ReviewReadDTO reviewReadDTO = reviewMapper.getReviewRead(TEST_REVIEW_RNO, TEST_EMAIL);
        Assertions.assertEquals(1, removeCount, "review Delete Not Success");
        log.info("=== End Delete Review Test Mapper ===");

    }

    @Test
    @Transactional
    @DisplayName("리뷰 수정 테스트")
    public void updateReviewTest() {

        // GIVEN
        log.info("===Start update Review Mapper Test");

        // WHEN
        int updateCount = reviewMapper.updateReveiw(reviewUpdateDTO);
        Long rno = reviewUpdateDTO.getRno();

        reviewFileMapper.deleteReviewImg(rno);

        List<String> fileNames = reviewUpdateDTO.getFileNames();

        if (updateCount > 0 && reviewUpdateDTO.getFileNames() != null && !reviewUpdateDTO.getFileNames().isEmpty()) {

            AtomicInteger index = new AtomicInteger();
            // 등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(list);

            reviewFileMapper.registReviewImg(list);
        }
    }

    @Test
    @Transactional
    @DisplayName("가맹점 리뷰 테스트")
    public void sotreReview(){

       List<ReviewListDTO> list =  reviewMapper.getReviewList(31L, pageRequestDTO);

       log.info("===================");
       log.info(list);
       log.info("===================");

    }

}
