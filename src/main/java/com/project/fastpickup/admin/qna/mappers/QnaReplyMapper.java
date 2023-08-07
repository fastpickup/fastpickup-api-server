package com.project.fastpickup.admin.qna.mappers;

import com.project.fastpickup.admin.qna.dto.reply.*;
import com.project.fastpickup.admin.util.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaReplyMapper {

//    // Create
//    int createQnaReply(QnaReplyRegistDTO qnaReplyRegistDTO);

    // read
    QnaReplyReadDTO readQnaReply(Long qno);

//    // update read
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
