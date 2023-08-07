package com.project.fastpickup.product.service;

/*
 * Date   : 2023.07.28
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
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
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductServiceTests {
  //의존성 주입
  @Autowired
  private ProductService productService;

  //Test 시작시 메모리에 private static final 로 먼저 올려놓는다
  private static final Long TEST_PNO = 22L;
  private static final String TEST_PRODUCT_NAME = "Junit Product Name Service Test";
  private static final String TEST_PRODUCT_CONTENT = "Junit Product Content Service Test";
  private static final int TEST_PRODUCT_PRICE = 4000;
  private static final int TEST_PRODUCT_RECOMMEND = 0;
  private static final long TEST_PRODUCT_STORE = 1L;
  private static final String TEST_FILE_NAME = "_Junit1.jpg";
  private static final String TEST_CATEGORY_NAME = "Junit Category Service Test";

  //DTO 정의
  private ProductDTO productDTO;
  private ProductListDTO productListDTO;
  private ProductRegistDTO productRegistDTO;
  private PageRequestDTO pageRequestDTO;
  private ProductCategoryDTO productCategoryDTO;

  //@BeforeEach 사용을 위한 셋팅
  @BeforeEach
  public void init(){
    //상품 등록
    productRegistDTO = ProductRegistDTO.builder()
      .productName(TEST_PRODUCT_NAME)
      .productContent(TEST_PRODUCT_CONTENT)
      .productPrice(TEST_PRODUCT_PRICE)
      .isRecommend(TEST_PRODUCT_RECOMMEND)
      .sno(TEST_PRODUCT_STORE)
      .fileNames(List.of(UUID.randomUUID() + TEST_FILE_NAME, UUID.randomUUID() + TEST_FILE_NAME))
      .build();

    //상품 등록시 카테고리 등록
    productCategoryDTO = ProductCategoryDTO.builder()
      .categoryName(TEST_CATEGORY_NAME)
      .pno(TEST_PNO)
      .build();

    //상품 리스트
    pageRequestDTO = PageRequestDTO.builder().build();

    //상품 수정
    productDTO = ProductDTO.builder()
      .pno(TEST_PNO)
      .productName(TEST_PRODUCT_NAME)
      .productContent(TEST_PRODUCT_CONTENT)
      .productPrice(TEST_PRODUCT_PRICE)
      .isRecommend(TEST_PRODUCT_RECOMMEND)
      .build();
  }

  //Create Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 등록 서비스 테스트")
  public void testCreateProduct(){
    //GIVEN
    log.info("=== Start Create Product Test Service ===");
    //WHEN
    int createCount = productService.createProduct(productRegistDTO, productCategoryDTO);
    //THEN
    Assertions.assertEquals(createCount, 1, "Product Register Test Fail");
    log.info("=== End Create Product Test Service ===");
  }

  //List Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 리스트 서비스 테스트")
  public void testListProduct(){
    //GIVEN
    log.info("=== Start List Product Test Service ===");
    //WHEN
    PageResponseDTO<ProductListDTO> list = productService.getList(pageRequestDTO);
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Product List is Null");
    log.info("=== End List Product Test Service ===");
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

  //Delete Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 삭제 서비스 테스트")
  public void testDeleteProduct(){
    //GIVEN
    log.info("=== Start Delete Product Test Service ===");
    //WHEN
    int deleteCount = productService.deleteProduct(TEST_PNO);
    //THEN
    ProductDTO dto = productService.selectOne(TEST_PNO);
    Assertions.assertEquals(1, deleteCount, "Product Delete Not Success");
    Assertions.assertNotNull(dto, "Product Delete is Null");
    Assertions.assertFalse(dto.isDeletedProduct(), "Product Delete is Not False");
    log.info("=== End Delete Product Test Service ===");
  }

  //Update Product Service Test
  @Test
  @Transactional
  @DisplayName("상품 수정 서비스 테스트")
  public void testUpdateProduct(){
    //GIVEN
    log.info("=== Start Update Product Test Service ===");
    //WHEN
    int updateCount = productService.updateProduct(productDTO, productCategoryDTO);
    //THEN
    ProductDTO dto = productService.selectOne(TEST_PNO);
    Assertions.assertEquals(1, updateCount, "Product Update Not Success");
    Assertions.assertNotNull(dto, "Product Update is Null");
    //Assertions.assertEquals(TEST_PRODUCT_NAME, "Junit Product Name Mapper Test2");
    log.info("=== End Update Product Test Service ===");
  }

}
