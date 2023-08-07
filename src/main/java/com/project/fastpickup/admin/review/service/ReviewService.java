package com.project.fastpickup.admin.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

@Transactional
public interface ReviewService {

    // 맴버 별 리뷰
    PageResponseDTO<ReviewListDTO> reviewMemberList(String email,PageRequestDTO pageRequestDTO);

    // 맴버 리뷰 상세
    ReviewReadDTO reviewRead(Long rno, String email);
    
}
