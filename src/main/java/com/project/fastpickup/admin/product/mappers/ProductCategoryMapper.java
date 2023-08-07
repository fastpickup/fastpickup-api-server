package com.project.fastpickup.admin.product.mappers;

/*
 * Date   : 2023.07.28
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;

public interface ProductCategoryMapper {
  //Create Product Category
  int createCategory(ProductCategoryDTO productCategoryDTO);

  //Update Product Category
  int updateCategory(ProductCategoryDTO productCategoryDTO);
}
