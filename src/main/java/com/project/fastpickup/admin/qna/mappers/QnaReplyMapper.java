package com.project.fastpickup.admin.qna.mappers;

/*
 * Date   : 2023.08.04
 * Author : 송수정
 * Author : 이범수
 * E-mail : sujung033131@gmail.com
 * E-mail : beomsu_1221@naver.com
 */

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
