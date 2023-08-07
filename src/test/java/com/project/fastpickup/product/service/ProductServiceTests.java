package com.project.fastpickup.product.service;

/*
 * Date   : 2023.08.07
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.service.ProductService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Log4j2
public class ProductServiceTests {
  //의존성 주입
  @Autowired
  private ProductService productService;

  //Test 시작시 메모리에 private static final 로 먼저 올려놓는다
  private static final Long TEST_PNO = 22L;
  private static final long TEST_PRODUCT_STORE = 1L;

  //DTO 정의
  private PageRequestDTO pageRequestDTO;

  //@BeforeEach 사용을 위한 셋팅
  @BeforeEach
  public void init(){
    //상품 리스트
    pageRequestDTO = PageRequestDTO.builder().build();
  }

  //List Category Service Test
  @Test
  @Transactional
  @DisplayName("카테고리 리스트 서비스 테스트")
  public void testListCategory(){
    //GIVEN
    log.info("=== Start List Category Test Service ===");
    //WHEN
    List<ProductCategoryDTO> list = productService.getCateList();
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Category List is Null");
    log.info("=== End List Category Test Service ===");
  }

  //List Store Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 가맹점 리스트 서비스 테스트")
  public void testListStoreProduct(){
    //GIVEN
    log.info("=== Start List Store Product Test Service ===");
    //WHEN
    PageResponseDTO<ProductListDTO> list = productService.getStoreList(pageRequestDTO, TEST_PRODUCT_STORE);
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Product List Store is Null");
    log.info("=== End List Store Product Test Service ===");
  }

  //Read Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 조회 서비스 테스트")
  public void testReadProduct(){
    //GIVEN
    log.info("=== Start Read Product Test Service ===");
    //WHEN
    ProductDTO dto = productService.selectOne(TEST_PNO);
    //THEN
    log.info(dto);
    Assertions.assertNotNull(dto, "Product Read is Null");
    log.info("=== End Read Product Test Service ===");
  }

}
