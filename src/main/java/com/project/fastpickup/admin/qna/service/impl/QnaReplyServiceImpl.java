package com.project.fastpickup.admin.qna.service.impl;

import com.project.fastpickup.admin.qna.dto.reply.QnaReplyReadDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyRegistDTO;
import com.project.fastpickup.admin.qna.dto.reply.QnaReplyUpdateDTO;
import com.project.fastpickup.admin.qna.mappers.QnaReplyMapper;
import com.project.fastpickup.admin.qna.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaReplyServiceImpl implements QnaReplyService {

    private final QnaReplyMapper qnaReplyMapper;

//    // create
//    @Override
//    public int createQnaReply(QnaReplyRegistDTO qnaReplyRegistDTO) {
//        return qnaReplyMapper.createQnaReply(qnaReplyRegistDTO);
//    }

    // read
    @Override
    public QnaReplyReadDTO readQnaReply(Long qno) {
        return qnaReplyMapper.readQnaReply(qno);
    }

//    // update read
//    @Override
//    public QnaReplyReadDTO readQnaReplyRno(Long rno) {
//        return qnaReplyMapper.readQnaReplyRno(rno);
//    }
//
//    // update
//    @Override
//    public int updateQnaReply(QnaReplyUpdateDTO qnaReplyUpdateDTO) {
//        return qnaReplyMapper.updateQnaReply(qnaReplyUpdateDTO);
//    }
//
//    // delete
//    @Override
//    public int deleteQnaReply(Long rno) {
//        return qnaReplyMapper.deleteQnaReply(rno);
//    }
//
//    // reply count
//    @Override
//    public int replyCount(Long qno) {
//        return qnaReplyMapper.replyCount(qno);
//    }
}
