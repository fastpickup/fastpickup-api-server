package com.project.fastpickup.admin.qna.service;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QnaService {

    // list
    PageResponseDTO<QnaListDTO> listQna (String email, PageRequestDTO pageRequestDTO);

    // Create
    int createQna (QnaRegistDTO qnaRegistDTO);

    // read
    QnaDTO readQna (Long qno);

    // update
    int updateQna(QnaUpdateDTO qnaUpdateDTO);

    // delete
    int deleteQna (Long qno);
}
