package com.project.fastpickup.admin.product.dto;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {
  //변수
  private Long pno;                     //PK
  private String productName;           //상품명
  private String productContent;        //상품상세
  private int productPrice;             //상품가격
  private LocalDateTime registDate;     //상품 등록일
  private int viewCount;                //상품 조회수
  private int likeCount;                //상품 좋아요
  private int isRecommend;              //추천상품여부
  private boolean isDeletedProduct;     //상품삭제여부
  private long sno;                     //가맹점 번호
  private String storeName;             //가맹점명
  private long cno;                     //카테고리번호
  private String categoryName;          //카테고리명
  private String fileName;              //파일명
  private int recStatus;                //추천상품 여부
}
