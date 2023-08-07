package com.project.fastpickup.admin.qna.restcontroller;

import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/qna/replies/")
@CrossOrigin
public class QnaReplyRestController {

    private final QnaReplyService qnaReplyService;

    // qno에 대한 댓글 조회 - qno 기준
    @GetMapping("{qno}")
    public QnaReplyReadDTO readQno(@PathVariable("qno") Long qno) {

        return qnaReplyService.readQnaReply(qno);
    }
}
