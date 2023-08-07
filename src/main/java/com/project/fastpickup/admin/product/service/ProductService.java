package com.project.fastpickup.admin.product.service;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ProductService {
  //Create Product
  int createProduct(ProductRegistDTO productRegistDTO, ProductCategoryDTO productCategoryDTO);

  //List Product
  PageResponseDTO<ProductListDTO> getList(PageRequestDTO pageRequestDTO);

  //List Store Product
  PageResponseDTO<ProductListDTO> getStoreList(PageRequestDTO pageRequestDTO, Long sno);

  //Read Product
  ProductDTO selectOne(Long pno);

  //Delete Product
  int deleteProduct(Long pno);

  //Update Product
  int updateProduct(ProductDTO productDTO, ProductCategoryDTO productCategoryDTO);

  //Update ViewCount
  int viewCount(Long pno);

}
