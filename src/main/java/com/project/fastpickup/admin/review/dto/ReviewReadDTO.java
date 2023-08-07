package com.project.fastpickup.admin.review.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class ReviewReadDTO {

    private Long rno;                   // 리뷰번호 PK
    private Long ono;                   // 주문 번호
    private Long sno;                   // 가맹점 번호
    private String storeName;           // 가맹점 이름
    private Long pno;                   // 상품 번호
    private String productName;         // 상품 이름
    private String email;               // 작성자
    private String reviewTitle;         // 리뷰 제목
    private String reviewContent;       // 리뷰 내용
    private List<String> fileNames;     // 리뷰 사진
    private LocalDateTime reviewDate;   // 리뷰 작성일
    private boolean isDeleted;          // 삭제여부



    
}
