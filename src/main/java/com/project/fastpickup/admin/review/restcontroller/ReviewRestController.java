package com.project.fastpickup.admin.review.restcontroller;

/*
 * Date   : 2023.08.04
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.dto.ReviewUpdateDTO;
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
    @PreAuthorize("permitAll")
    @GetMapping("/member/{email}")
    public PageResponseDTO<ReviewListDTO> getReviewMemberPage(
        @PathVariable("email") String email,
        PageRequestDTO pageRequestDTO
    ){
         return reviewService.reviewMemberList(email, pageRequestDTO);
       
    }

    // 맴버가 쓴 리뷰 상세 페이지
    @PreAuthorize("permitAll")
    @GetMapping("/read/{rno}")
    public ReviewReadDTO getReveiwRead(
        @PathVariable("rno") Long rno
    ){
        return reviewService.reviewRead(rno);
    }

    // 가맹점이 쓴 리뷰 답글
    @PreAuthorize("permitAll")
    @GetMapping("/store/read/{rno}")
    public ReviewReadDTO getStoreReview(
        @PathVariable("rno") Long rno
    ){
        return reviewService.storeReview(rno);
    }

    // 가맹점 별 리뷰 리스트
    @PreAuthorize("permitAll")
    @GetMapping("/store/{sno}")
    public PageResponseDTO<ReviewReadDTO> getReviewPage(
        @PathVariable("sno") Long sno,
        PageRequestDTO pageRequestDTO
    ){
        return reviewService.storeReviewList(sno, pageRequestDTO);
      
    }
    
    // 리뷰 등록
    @PreAuthorize("permitAll")
    @PostMapping("/regist")
    public Long registReview(@RequestBody ReviewRegistDTO reviewRegistDTO) {

        return reviewService.registReview(reviewRegistDTO);
    } 

    // 리뷰 삭제
    @PreAuthorize("permitAll")
    @PutMapping("/remove/{rno}")
    public Long removeReview(@PathVariable("rno") Long rno) {

        return reviewService.removeReview(rno);
    }

    // 리뷰 수정
    @PreAuthorize("permitAll")
    @PutMapping("/update")
    public Long updateReveiw(@RequestBody ReviewUpdateDTO reviewUpdateDTO){

        return reviewService.updateReview(reviewUpdateDTO);

    }


}
