package com.project.fastpickup.admin.review.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.dto.ReviewUpdateDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

public interface ReviewMapper {

    // 사용자 리뷰 전체 리스트
    List<ReviewListDTO> getReviewMemberList(@Param("email")String email, @Param("pr")PageRequestDTO pageRequestDTO);
    // 검색 조건 / 페이징을 위한 카운트
    int reviewMemberListCount(@Param("email")String email, @Param("pr")PageRequestDTO pageRequestDTO);
    

    // 가맹점 전체 리뷰 리스트
    List<ReviewListDTO> getReviewList(@Param("sno") Long sno, @Param("pr") PageRequestDTO pageRequestDTO);

    // 사용자 리뷰 상세
    ReviewReadDTO getReviewRead(@Param("rno")Long rno, @Param("email") String email);

    // 리뷰 작성
    int registReview(ReviewRegistDTO reviewRegistDTO);
    int updateGno(Long rno);

    // 리뷰 삭제
    int removeReview(Long rno);

    // 리뷰 수정
    int updateReveiw(ReviewUpdateDTO reviewUpdateDTO);

}
