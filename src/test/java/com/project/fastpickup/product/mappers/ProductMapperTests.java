package com.project.fastpickup.product.mappers;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import com.project.fastpickup.admin.product.dto.ProductCategoryDTO;
import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.dto.ProductListDTO;
import com.project.fastpickup.admin.product.dto.ProductRegistDTO;
import com.project.fastpickup.admin.product.mappers.FileMapper;
import com.project.fastpickup.admin.product.mappers.ProductCategoryMapper;
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
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

//Product Mapper Test Class
@SpringBootTest
@Log4j2
public class ProductMapperTests {
  //의존성 주입
  @Autowired(required = false)
  private ProductMapper productMapper;

  @Autowired(required = false)
  private FileMapper fileMapper;

  @Autowired(required = false)
  private ProductCategoryMapper categoryMapper;

  //Test 시작시 메모리에 private static final 로 먼저 올려놓는다
  private static final Long TEST_PNO = 22L;
  private static final String TEST_PRODUCT_NAME = "Junit Product Name Mapper Test";
  private static final String TEST_PRODUCT_CONTENT = "Junit Product Content Mapper Test";
  private static final int TEST_PRODUCT_PRICE = 5000;
  private static final int TEST_PRODUCT_RECOMMEND = 0;
  private static final long TEST_PRODUCT_STORE = 1L;
  private static final String TEST_FILE_NAME = "_Junit.jpg";
  private static final String TEST_CATEGORY_NAME = "Junit Category Name Mapper Test";

  //DTO 정의
  private ProductDTO productDTO;
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
      //.isRecommend(TEST_PRODUCT_RECOMMEND)
      .sno(TEST_PRODUCT_STORE)
      .fileNames(List.of(UUID.randomUUID() + TEST_FILE_NAME, UUID.randomUUID() + TEST_FILE_NAME))
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

  //Create Product Mapper Test
  @Test
  //@Transactional
  @DisplayName("상품 등록 매퍼 테스트")
  public void testCreateProduct(){
    //GIVEN
    log.info("=== Start Create Product Test Mapper ===");
    //WHEN
    int registCount = productMapper.createProduct(productRegistDTO);
    //상품 등록한 pno가져오기 위함
    Long pno = productRegistDTO.getPno();
    log.info("등록한 상품번호: " + pno);

    //상품 등록시 카테고리 등록
    productCategoryDTO = ProductCategoryDTO.builder()
      .categoryName(TEST_CATEGORY_NAME)
      .pno(pno)
      .build();

    int categoryCount = categoryMapper.createCategory(productCategoryDTO);
    long categoryPno = productCategoryDTO.getPno();
    Long cno = productCategoryDTO.getCno();
    log.info("등록한 카테고리 상품번호: " + categoryPno);
    log.info("등록한 카테고리 번호: " + cno);

    //파일이름 List로 가져오기
    List<String> fileNames = productRegistDTO.getFileNames();

    //상품 등록 성공과 파일이 등록되었다면 실행
    if(registCount > 0 && productRegistDTO.getFileNames() != null && !productRegistDTO.getFileNames().isEmpty()) {
      AtomicInteger index = new AtomicInteger();
      //등록된 파일 fileNames에서 추출
      List<Map<String, String>> list = fileNames.stream().map(str -> {
        //_ 기준으로 문자열 자르기
        String[] splitStr = str.split("_");
        //uuid 가져오기
        String uuid = splitStr[0];
        //실제 파일명 가져오기
        String fileName = splitStr[1];
        //return map에 담기
        return Map.of("uuid", uuid, "fileName", fileName, "pno", "" + pno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      log.info(list);
      //파일 등록 실행
      fileMapper.createImage(list);
    }
    //THEN
    //ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, registCount, "Product Register Test Fail");
    Assertions.assertEquals(1, categoryCount, "Product Category Register Test Fail");
    log.info("=== End Create Product Test Mapper ===");
  }

  //List Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 리스트 매퍼 테스트")
  public void testListProduct(){
    //GIVEN
    log.info("=== Start List Product Test Mapper ===");
    //WHEN
    List<ProductListDTO> list = productMapper.getList(pageRequestDTO);
    //THEN
    log.info(list);
    Assertions.assertNotNull(list, "Product List is Null");
    log.info("=== End List Product Test Mapper ===");
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

  //Delete Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 삭제 매퍼 테스트")
  public void testDeleteProduct(){
    //GIVEN
    log.info("=== Start Delete Product Test Mapper ===");
    //WHEN
    int deleteCount = productMapper.deleteProduct(TEST_PNO);
    //THEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, deleteCount, "Product Delete Not Success");
    Assertions.assertNotNull(dto, "Product Delete is Null");
    Assertions.assertFalse(dto.isDeletedProduct(), "Product Delete is Not False");
    log.info("=== End Delete Product Test Mapper ===");
  }

  //Update Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 수정 매퍼 테스트")
  public void testUpdateProduct(){
    //GIVEN
    log.info("=== Start Update Product Test Mapper ===");
    //WHEN
    int updateCount = productMapper.updateProduct(productDTO);
    //THEN
    ProductDTO dto = productMapper.selectOne(TEST_PNO);
    Assertions.assertEquals(1, updateCount, "Product Update Not Success");
    Assertions.assertNotNull(dto, "Product Update is Null");
    //Assertions.assertEquals(TEST_PRODUCT_NAME, "Junit Product Name Mapper Test2");
    log.info("=== End Update Product Test Mapper ===");
  }

  //List Count Product Mapper Test
  @Test
  @Transactional
  @DisplayName("상품 리스트 토탈 매퍼 테스트")
  public void testListCountProduct(){
    //GIVEN
    log.info("=== Start List Count Product Test Mapper ===");
    //WHEN
    long listTotal = productMapper.listCount(pageRequestDTO);
    //THEN
    log.info(listTotal);
    Assertions.assertNotNull(listTotal, "Product List is Null");
    log.info("=== End List Count Product Test Mapper ===");
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
