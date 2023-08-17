package com.project.fastpickup.admin.qna.mappers;

/*
 * Date   : 2023.08.07
 * Author : 송수정
 * E-mail : sujung033131@gmail.com
 */

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {

    // list
    List<QnaListDTO> listQna (@Param("email") String email, @Param("pr") PageRequestDTO pageRequestDTO);

    // Create
    int createQna (QnaRegistDTO qnaRegistDTO);

    // read
    QnaDTO readQna (Long qno);

    // update
    int updateQna(QnaUpdateDTO qnaUpdateDTO);

    // delete
    int deleteQna (Long qno);

    // total
    long listCount (@Param("email") String email, @Param("pr") PageRequestDTO pageRequestDTO);

}
