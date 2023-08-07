package com.project.fastpickup.admin.product.dto;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCategoryDTO {
  //변수
  private Long cno;               //PK
  private String categoryName;    //카테고리명
  private long pno;               //상품번호 FK
}
