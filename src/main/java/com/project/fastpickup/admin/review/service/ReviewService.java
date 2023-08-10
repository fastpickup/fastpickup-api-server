package com.project.fastpickup.admin.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.dto.ReviewUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

@Transactional
public interface ReviewService {

    // 맴버 별 리뷰
    PageResponseDTO<ReviewListDTO> reviewMemberList(String email,PageRequestDTO pageRequestDTO);
    // 가맹점 별 리뷰
    PageResponseDTO<ReviewListDTO> reviewList(Long rno, PageRequestDTO pageRequestDTO);

    // 맴버 리뷰 상세
    ReviewReadDTO reviewRead(Long rno);

    // 가맹점 리뷰 답글
    ReviewReadDTO storeReview(Long rno);

    // 리뷰 등록
    Long registReview(ReviewRegistDTO reviewRegistDTO);

    // 리뷰 삭제
    Long removeReview(Long rno);

    // 리뷰 수정
    Long updateReview(ReviewUpdateDTO reviewUpdateDTO);
    
}
