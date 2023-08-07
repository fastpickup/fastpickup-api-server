package com.project.fastpickup.admin.product.dto;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRegistDTO {
  //변수
  private Long pno;                                         //PK
  private String productName;                               //상품명
  private String productContent;                            //상품상세
  private int productPrice;                                 //상품가격
  private int isRecommend;                                  //추천상품여부
  private long sno;                                         //가맹점 번호
  @Builder.Default
  private List<String> fileNames = new ArrayList<>();       //파일명
}
