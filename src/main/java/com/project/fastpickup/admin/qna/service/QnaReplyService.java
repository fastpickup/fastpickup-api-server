package com.project.fastpickup.admin.qna.service;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyRegistDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyUpdateDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface QnaReplyService {

//    // Create
//    int createQnaReply(QnaReplyRegistDTO qnaReplyRegistDTO);

    // reply read
    QnaReplyReadDTO readQnaReply(Long qno);

//    // reply Update read
//    QnaReplyReadDTO readQnaReplyRno(Long rno);
//
//    // Update
//    int updateQnaReply(QnaReplyUpdateDTO qnaReplyUpdateDTO);
//
//    // Delete
//    int deleteQnaReply(Long rno);
//
//    // reply count
//    int replyCount(Long qno);
}
