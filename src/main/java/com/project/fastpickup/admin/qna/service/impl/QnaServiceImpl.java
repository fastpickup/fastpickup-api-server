package com.project.fastpickup.admin.qna.service.impl;

import com.project.fastpickup.admin.qna.dto.QnaDTO;
import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.qna.dto.QnaRegistDTO;
import com.project.fastpickup.admin.qna.dto.QnaUpdateDTO;
import com.project.fastpickup.admin.qna.mappers.QnaMapper;
import com.project.fastpickup.admin.qna.service.QnaService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaMapper qnaMapper;

    // list
    @Override
    public PageResponseDTO<QnaListDTO> listQna(@Param("email") String email, PageRequestDTO pageRequestDTO) {
        List<QnaListDTO> list = qnaMapper.listQna("thistrik@naver.com",pageRequestDTO);
        long total = qnaMapper.listCount(pageRequestDTO);

        return PageResponseDTO.<QnaListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // Create
    @Override
    public int createQna(QnaRegistDTO qnaRegistDTO) {

        return qnaMapper.createQna(qnaRegistDTO);
    }

    // read
    @Override
    public QnaDTO readQna(Long qno) {

        return qnaMapper.readQna(qno);
    }

    // update
    @Override
    public int updateQna(QnaUpdateDTO qnaUpdateDTO) {

        return qnaMapper.updateQna(qnaUpdateDTO);
    }

    // delete
    @Override
    public int deleteQna(Long qno) {

        return qnaMapper.deleteQna(qno);
    }
}
