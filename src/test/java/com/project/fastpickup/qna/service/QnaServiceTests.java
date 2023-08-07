package com.project.fastpickup.qna.service;

import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.service.QnaService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class QnaServiceTests {

    // 의존성 주입
    @Autowired
    private QnaService qnaService;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다.
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_EMAIL1 = "wow_1@nate.com";
    private static final String TEST_QNA_TITLE = "문의합니다.";
    private static final String TEST_QNA_TITLE2 = "문의합니다.(수정본)";
    private static final String TEST_QNA_CONTENT = "문의내용은 ~~~~~~~입니다.";
    private static final String TEST_QNA_CONTENT2 = "문의내용은 ~~~~~~~입니다.(수정본)";

    // DTO 정의
    private QnaRegistDTO qnaRegistDTO;
    private QnaRegistDTO qnaRegistDTO2;
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

        // 문의 등록2
        qnaRegistDTO2 = QnaRegistDTO.builder()
                .email(TEST_EMAIL1)
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
    public void listQnaTestService() {
        // GIVEN
        log.info("=== Start List Qna Test Service ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<QnaListDTO> list = qnaService.listQna("9805121234@naver.com",pageRequestDTO);
        log.info(list);
        // THEN
        log.info("=== End List Qna Test Service ===");

    }

    // createQna test
//    @Test
//    @Transactional
//    @DisplayName("문의 등록")
//    public void createQnaTestService() {
//        // GIVEN
//        log.info("=== Start Create Qna Test Service ===");
//        // WHEN
//        qnaService.createQna(qnaRegistDTO2);
//        // THEN
//        Assertions.assertEquals(qnaRegistDTO2.getEmail(), "wow_1@nate.com");
//        log.info("=== End Create Qna Test Service ===");
//    }

    // readQna test
    @Test
    @Transactional
    @DisplayName("문의글 상세페이지")
    public void readQnaTestService() {
        // GIVEN
        log.info("=== Start Read Qna Test Service ===");
        // WHEN
        qnaService.readQna(23L);
        // THEN
        Assertions.assertEquals(qnaRegistDTO2.getEmail(), "wow_1@nate.com");
        log.info("=== End Read Qna Test Service ===");
    }

    // updateQna test
    @Test
    @Transactional
    @DisplayName("문의글 수정")
    public void updateQnaTestService() {
        // GIVEN
        log.info("=== Start update Qna Test Service ===");
        // WHEN
        int result = qnaService.updateQna(qnaUpdateDTO);
        // THEN
        Assertions.assertEquals(result, 1);
        log.info("=== End update Qna Test Service ===");
    }

    // deleteQna test
    @Test
    @Transactional
    @DisplayName("문의글 삭제")
    public void deleteQnaTestService() {
        // GIVEN
        log.info("=== Start delete Qna Test Service ===");
        // WHEN
        int result = qnaService.deleteQna(23L);
        // THEN
        Assertions.assertEquals(result, 1);
        log.info("=== End delete Qna Test Service ===");
    }

}
