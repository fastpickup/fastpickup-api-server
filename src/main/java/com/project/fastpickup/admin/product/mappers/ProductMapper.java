package com.project.fastpickup.admin.product.mappers;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
  //Create Product
  int createProduct(ProductRegistDTO productRegistDTO);

  //List Product
  List<ProductListDTO> getList(PageRequestDTO pageRequestDTO);
  //List count
  long listCount(PageRequestDTO pageRequestDTO);
  // /List Product

  //Store List Product
  List<ProductListDTO> getStoreList(@Param("pr") PageRequestDTO pageRequestDTO, @Param("sno") Long sno);
  //List count
  long listStoreCount(@Param("pr") PageRequestDTO pageRequestDTO, @Param("sno") Long sno);
  // /Store List Product

  //Read Product
  ProductDTO selectOne(Long pno);

  //Delete Product
  int deleteProduct(Long pno);

  //Update Product
  int updateProduct(ProductDTO productDTO);

  //Update ViewCount
  int viewCount(Long pno);

}
