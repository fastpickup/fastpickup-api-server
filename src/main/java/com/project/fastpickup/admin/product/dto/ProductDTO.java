package com.project.fastpickup.admin.product.dto;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  //변수
  private Long pno;                                         //PK
  private String productName;                               //상품명
  private String productContent;                            //상품상세
  private int productPrice;                                 //상품가격
  private LocalDateTime registDate;                         //상품 등록일
  private LocalDateTime updateDate;                         //상품 수정일
  private int viewCount;                                    //상품 조회수
  private int likeCount;                                    //상품 좋아요
  private int isRecommend;                                  //추천상품여부
  private boolean isDeletedProduct;                         //상품삭제여부
  private long sno;                                         //가맹점 번호
  private String storeName;                                 //가맹점명
  private long cno;                                         //카테고리번호
  private String categoryName;                              //카테고리명
  @Builder.Default
  private List<String> fileNames = new ArrayList<>();       //파일명
}
