package com.project.fastpickup.admin.review.dto;

/*
 * Date   : 2023.08.04
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import java.time.LocalDateTime;

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
public class ReviewListDTO {

    private Long rno;                 // 리뷰 번호 PK
    private Long ono;                 // 주문 번호
    private Long sno;                 // 가맹점 번호
    private String storeName;         // 가맹점 이름
    private Long pno;                 // 상품 번호
    private String productName;       // 상품 이름
    private String productImg;        // 상품 사진
    private LocalDateTime reviewDate; // 리뷰 작성 일자
    private boolean isDeleted;        // 리뷰 삭제 여부
    
}
