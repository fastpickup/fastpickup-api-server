package com.project.fastpickup.admin.review.restcontroller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewRestController {

    private final ReviewService reviewService;

    // 맴버가 쓴 리뷰 리스트
    @GetMapping("/{email}")
    public PageResponseDTO<ReviewListDTO> getReviewMemberPage(
        @PathVariable("email") String email,
        PageRequestDTO pageRequestDTO
    ){
        return reviewService.reviewMemberList(email, pageRequestDTO);
    }

    // 맴버가 쓴 리뷰 상세 페이지
    @GetMapping("/{email}/{rno}")
    public ReviewReadDTO getReveiwRead(
        @PathVariable("email") String email,
        @PathVariable("rno") Long rno
    ){
        return reviewService.reviewRead(rno, email);
    }


}
