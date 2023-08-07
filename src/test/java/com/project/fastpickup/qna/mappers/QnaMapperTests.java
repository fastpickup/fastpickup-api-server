package com.project.fastpickup.qna.mappers;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.mappers.QnaMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Log4j2
public class QnaMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private QnaMapper qnaMapper;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다.
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_QNA_TITLE = "문의합니다.";
    private static final String TEST_QNA_TITLE2 = "문의합니다.(수정본)";
    private static final String TEST_QNA_CONTENT = "문의내용은 ~~~~~~~입니다.";
    private static final String TEST_QNA_CONTENT2 = "문의내용은 ~~~~~~~입니다.(수정본)";

    // DTO 정의
    private QnaRegistDTO qnaRegistDTO;
    private QnaUpdateDTO qnaUpdateDTO;

    // BeforeEach 사용을 위한 setUp
    @BeforeEach
    public void setUp() {

        // 문의 등록
        qnaRegistDTO = QnaRegistDTO.builder()
                .email(TEST_EMAIL)
                .qnaTitle(TEST_QNA_TITLE)
                .qnaContent(TEST_QNA_CONTENT)
                .build();

        // 문의 수정
        qnaUpdateDTO = QnaUpdateDTO.builder()
                .qno(23L)
                .qnaTitle(TEST_QNA_TITLE2)
                .qnaContent(TEST_QNA_CONTENT2)
                .build();
    }

    /*  TEST  */
    // listQna test
    @Test
    @Transactional
    @DisplayName("문의 리스트")
    public void listQnaTestMapper() {
        // GIVEN
        log.info("=== Start List Qna Test Mapper ===");
        // WHEN
        PageRequestDTO list = PageRequestDTO.builder().build();
        List<QnaListDTO> qnaList = qnaMapper.listQna("9805121234@naver.com",list);
        log.info(qnaList);
        // THEN
        log.info("=== End List Qna Test Mapper ===");
    }

    // listCount test
    @Test
    @Transactional
    @DisplayName("리스트 총 개수")
    public void listCountTestMapper() {
        // GIVEN
        log.info("=== Start List Count Test Mapper ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        qnaMapper.listCount(pageRequestDTO);
        // THEN
        log.info("=== End List Count Test Mapper ===");

    }



    // createQna test
//    @Test
//    @Transactional
//    @DisplayName("문의 등록")
//    public void createQnaTestMapper() {
//        // GIVEN
//        log.info("=== Start Create Qna Test Mapper ===");
//        // WHEN
//        qnaMapper.createQna(qnaRegistDTO);
//        // THEN
//        Assertions.assertEquals(qnaRegistDTO.getEmail(), "thistrik@naver.com");
//        log.info("=== End Create Qna Test Mapper ===");
//    }

    // readQna test
    @Test
    @Transactional
    @DisplayName("문의글 상세페이지")
    public void readQnaTestMapper() {
        // GIVEN
        log.info("=== Start Read Qna Test Mapper ===");
        // WHEN
        QnaDTO readQna = qnaMapper.readQna(23L);
        // THEN
        Assertions.assertEquals(readQna.getQno(), 23L);
        log.info("=== End Read Qna Test Mapper ===");
    }

    // updateQna test
    @Test
    @Transactional
    @DisplayName("문의글 수정")
    public void updateQnaTestMapper() {
        // GIVEN
        log.info("=== Start Update Qna Test Mapper ===");
        // WHEN
        int result = qnaMapper.updateQna(qnaUpdateDTO);
        // THEN
        Assertions.assertEquals(result, 1);
        log.info("=== End Update Qna Test Mapper ===");

    }

    // deleteQna test
    @Test
    @Transactional
    @DisplayName("문의글 삭제")
    public void deleteQnaTestMapper() {
        // GIVEN
        log.info("=== Start Delete Qna Test Mapper ===");
        // WHEN
        int result = qnaMapper.deleteQna(23L);
        // THEN
        Assertions.assertEquals(result, 1);
        log.info("=== End Delete Qna Test Mapper ===");
    }




}
