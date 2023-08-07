package com.project.fastpickup.product.mappers;

/*
 * Date   : 2023.08.07
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.mappers.ProductMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//Product Mapper Test Class
@SpringBootTest
@Log4j2
public class ProductMapperTests {
  //의존성 주입
  @Autowired(required = false)
  private ProductMapper productMapper;

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

  //List Category Mapper Test
  @Test
  @Transactional
  @DisplayName("카테고리 리스트 매퍼 테스트")
  public void testListCategory(){
    //GIVEN
    log.info("=== Start List Category Test Mapper ===");
    //WHEN
    List<ProductCategoryDTO> list = productMapper.getCateList();
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Category List is Null");
    log.info("=== End List Category Test Mapper ===");
  }

  //List Store Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 가맹점 리스트 매퍼 테스트")
  public void testListStoreProduct(){
    //GIVEN
    log.info("=== Start List Store Product Test Mapper ===");
    //WHEN
    List<ProductListDTO> list = productMapper.getStoreList(pageRequestDTO, TEST_PRODUCT_STORE);
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Product Store List is Null");
    log.info("=== End List Store Product Test Mapper ===");
  }

  //Read Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 조회 매퍼 테스트")
  public void testReadProduct(){
    //GIVEN
    log.info("=== Start Read Product Test Mapper ===");
    //WHEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    //THEN
    log.info(dto);
    Assertions.assertNotNull(dto, "Product Read is Null");
    log.info("=== End Read Product Test Mapper ===");
  }

  //List Count Store Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 가맹점 리스트 토탈 매퍼 테스트")
  public void testListStoreCountProduct(){
    //GIVEN
    log.info("=== Start List Count Store Product Test Mapper ===");
    //WHEN
    long listTotal = productMapper.listStoreCount(pageRequestDTO, TEST_PRODUCT_STORE);
    //THEN
    log.info(listTotal);
    Assertions.assertNotNull(listTotal, "Product Store List is Null");
    log.info("=== End List Count Store Product Test Mapper ===");
  }

}
