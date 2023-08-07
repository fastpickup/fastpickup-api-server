package com.project.fastpickup.admin.product.service;

/*
 * Date   : 2023.08.07
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ProductService {
  //List Category
  List<ProductCategoryDTO> getCateList();

  //List Store Product
  PageResponseDTO<ProductListDTO> getStoreList(PageRequestDTO pageRequestDTO, Long sno);

  //Read Product
  ProductDTO selectOne(Long pno);

  //Update ViewCount
  int viewCount(Long pno);

}
