package com.project.fastpickup.admin.review.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.mappers.ReviewFileMapper;
import com.project.fastpickup.admin.review.mappers.ReviewMapper;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    // 의존성 주입
    private final ReviewMapper reviewMapper;
    private final ReviewFileMapper reviewFileMapper;

    // 맴버 리뷰 리스트 
    @Override
    public PageResponseDTO<ReviewListDTO> reviewMemberList(String email, PageRequestDTO pageRequestDTO) {

        List<ReviewListDTO> list = reviewMapper.getReviewMemberList(email, pageRequestDTO);
        int total = reviewMapper.reviewMemberListCount(email, pageRequestDTO);

        return PageResponseDTO.<ReviewListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // 리뷰 상세
    @Override
    public ReviewReadDTO reviewRead(Long rno, String email) {

        return reviewMapper.getReviewRead(rno, email);

    }

}
