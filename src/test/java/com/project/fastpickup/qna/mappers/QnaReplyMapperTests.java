package com.project.fastpickup.qna.mappers;

import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyRegistDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyUpdateDTO;
import com.project.fastpickup.admin.qna.mappers.QnaReplyMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Log4j2
public class QnaReplyMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private QnaReplyMapper qnaReplyMapper;

    // Test 시작시 메모리에 priavte static final 로 먼저 올려놓는다.
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_QNA_REPIY = "문의하신 내용의 답변은 ~~~~~~~입니다.";
    private static final String TEST_QNA_REPIY2 = "문의하신 내용의 답변은 ~~~~~~~입니다.(수정본)";

    // DTO 정의
    private QnaReplyRegistDTO qnaReplyRegistDTO;
    private QnaReplyUpdateDTO qnaReplyUpdateDTO;

    // BeforeEach 사용을 위한 setUp
    @BeforeEach
    public void setUp() {

        // 문의 답글 등록
        qnaReplyRegistDTO = QnaReplyRegistDTO.builder()
                .qno(23L)
                .reply(TEST_QNA_REPIY)
                .email(TEST_EMAIL)
                .build();

        // 문의 답글 수정
        qnaReplyUpdateDTO = QnaReplyUpdateDTO.builder()
                .rno(49L)
                .qno(23L)
                .reply(TEST_QNA_REPIY2)
                .build();
    }

    /*  TEST  */
    // createQnaReply test
//    @Test
//    @Transactional
//    @DisplayName("문의 답글 등록")
//    public void createQnaReplyTestMapper() {
//        // GIVEN
//        log.info("=== Start Create Qna Reply Test Mapper ===");
//        // WHEN
//        qnaReplyMapper.createQnaReply(qnaReplyRegistDTO);
//        // THEN
//        Assertions.assertEquals(qnaReplyRegistDTO.getEmail(), "thistrik@naver.com");
//        log.info("=== End Create Qna Reply Test Mapper ===");
//    }

    // readQnaReply test
    @Test
    @Transactional
    @DisplayName("문의 답글 조회")
    public void readQnaReplyTestMapper() {
        // GIVEN
        log.info("=== Start Read Qna Test Mapper ===");
        // WHEN
        QnaReplyReadDTO readQnaReply = qnaReplyMapper.readQnaReply(23L);
        // THEN
        Assertions.assertNotNull(readQnaReply, "Qna Reply Read is Null");
        log.info("=== End Read Qna Test Mapper ===");
    }

//     // readQnaReplyRno test
//    @Test
//    @Transactional
//    @DisplayName("문의 답글 수정시 조회")
//    public void readQnaReplyRnoTestMapper() {
//        // GIVEN
//        log.info("=== Start Read Qna Test Mapper ===");
//        // WHEN
//        QnaReplyReadDTO readQnaReplyrno = qnaReplyMapper.readQnaReplyRno(49L);
//        // THEN
//        Assertions.assertNotNull(readQnaReplyrno, "Qna Reply Read is Null");
//        log.info("=== End Read Qna Test Mapper ===");
//    }
//
//    // updateQnaReply test
//    @Test
//    @Transactional
//    @DisplayName("문의 답글 수정")
//    public void updateQnaTestMapper() {
//        // GIVEN
//        log.info("=== Start Update Qna Reply Test Mapper ===");
//        // WHEN
//        int result = qnaReplyMapper.updateQnaReply(qnaReplyUpdateDTO);
//        // THEN
//        Assertions.assertEquals(result, 1);
//        log.info("=== End Update Qna Reply Test Mapper ===");
//    }
//
//    // deleteQnaReply test
//    @Test
//    @Transactional
//    @DisplayName("문의 답글 삭제")
//    public void deleteQnaReplyTestMapper() {
//        // GIVEN
//        log.info("=== Start Delete Qna Reply Test Mapper ===");
//        // WHEN
//        int result = qnaReplyMapper.deleteQnaReply(49L);
//        // THEN
//        //Assertions.assertEquals(result, 1);
//        log.info("=== End Delete Qna Reply Test Mapper ===");
//    }
//
//    // reply count
//    @Test
//    @Transactional
//    @DisplayName("댓글 개수 파악")
//    public void replyCountMapperTest(){
//        // GIVEN
//        log.info("=== Start Count Qna Reply Test Mapper ===");
//        // WHEN
//        int count = qnaReplyMapper.replyCount(23L);
//        // THEN
//        log.info("count : " + count);
//        log.info("=== End Count Qna Reply Test Mapper ===");
//    }

}
