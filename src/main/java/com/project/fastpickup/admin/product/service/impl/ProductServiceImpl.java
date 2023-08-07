package com.project.fastpickup.admin.product.service.impl;

/*
 * Date   : 2023.08.07
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.mappers.ProductMapper;
import com.project.fastpickup.admin.product.service.ProductService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

  //의존성 주입
  private final ProductMapper productMapper;

  @Override
  public List<ProductCategoryDTO> getCateList() {
    //카테고리 리스트만 출력하여 바로 return
    return productMapper.getCateList();
  }

  //List Store Product
  @Override
  public PageResponseDTO<ProductListDTO> getStoreList(PageRequestDTO pageRequestDTO, Long sno) {
    log.info("============ Product List Store Service ============");
    //리스트 선언
    List<ProductListDTO> list = productMapper.getStoreList(pageRequestDTO, sno);
    //Total 선언
    long total = productMapper.listStoreCount(pageRequestDTO, sno);

    log.info("============ //Product List Store Service ============");
    //PageResponseDTO 타입으로 반환
    return PageResponseDTO.<ProductListDTO>withAll()
      .list(list)
      .total(total)
      .pageRequestDTO(pageRequestDTO)
      .build();
  }

  //Read Product
  @Override
  public ProductDTO selectOne(Long pno) {
    log.info("============ Product Read Service ============");
    ProductDTO productDTO = productMapper.selectOne(pno);
    log.info("============ //Product Read Service ============");
    return productDTO;
  }

  @Override
  public int viewCount(Long pno) {
    return productMapper.viewCount(pno);
  }
}
