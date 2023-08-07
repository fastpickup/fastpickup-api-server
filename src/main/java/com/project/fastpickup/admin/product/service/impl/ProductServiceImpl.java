package com.project.fastpickup.admin.product.service.impl;

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
import com.project.fastpickup.admin.product.service.ProductService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

  //의존성 주입
  private final ProductMapper productMapper;
  private final ProductCategoryMapper categoryMapper;
  private final FileMapper fileMapper;

  //Create Product
  @Override
  public int createProduct(ProductRegistDTO productRegistDTO, ProductCategoryDTO categoryDTO) {
    log.info("============ Product Create Service ============");
    int registCount = productMapper.createProduct(productRegistDTO);
    Long pno = productRegistDTO.getPno();

    //상품 등록후 카테고리 pno 업데이트
    categoryDTO.setPno(pno);
    categoryMapper.createCategory(categoryDTO);

    //파일이름 List로 가져오기
    List<String> fileNames = productRegistDTO.getFileNames();

    //상품 등록 성공과 파일이 등록되었다면 실행
    if(registCount > 0 && productRegistDTO.getFileNames() != null && !productRegistDTO.getFileNames().isEmpty()) {
      AtomicInteger index = new AtomicInteger();
      //등록된 파일 fileNames에서 추출
      List<Map<String, String>> list = fileNames.stream().map(str -> {
        //_ 기준으로 문자열 자르기
        //String[] splitStr = str.split("_");
        //uuid 가져오기
        String uuid = str.substring(0, 36);
        //실제 파일명 가져오기
        String fileName = str.substring(37);
        //return map에 담기
        return Map.of("uuid", uuid, "fileName", fileName, "pno", "" + pno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      log.info(list);
      //파일 등록 실행
      fileMapper.createImage(list);
    }//end if
    log.info("============ //Product Create Service ============");
    return registCount;
  }

  //List Product
  @Override
  public PageResponseDTO<ProductListDTO> getList(PageRequestDTO pageRequestDTO) {
    log.info("============ Product List Service ============");
    //리스트 선언
    List<ProductListDTO> list = productMapper.getList(pageRequestDTO);
    //Total 선언
    long total = productMapper.listCount(pageRequestDTO);

    log.info("============ //Product List Service ============");
    //PageResponseDTO 타입으로 반환
    return PageResponseDTO.<ProductListDTO>withAll()
      .list(list)
      .total(total)
      .pageRequestDTO(pageRequestDTO)
      .build();
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

  //Delete Product
  @Override
  public int deleteProduct(Long pno) {
    log.info("============ Product Delete Service ============");
    int deleteCount = productMapper.deleteProduct(pno);
    log.info("============ //Product Delete Service ============");
    return deleteCount;
  }

  //Update Product
  @Override
  public int updateProduct(ProductDTO productDTO, ProductCategoryDTO categoryDTO) {
    log.info("============ Product Update Service ============");
    int updateCount = productMapper.updateProduct(productDTO);
    Long pno = productDTO.getPno();

    //상품 등록후 카테고리 pno 업데이트
    categoryDTO.setPno(pno);
    categoryMapper.updateCategory(categoryDTO);

    //기존 파일 삭제
    fileMapper.deleteImage(pno);

    //파일이름 List로 가져오기
    List<String> fileNames = productDTO.getFileNames();

    //상품 등록 성공과 파일이 등록되었다면 실행
    if(updateCount > 0 && productDTO.getFileNames() != null && !productDTO.getFileNames().isEmpty()) {
      AtomicInteger index = new AtomicInteger();
      //등록된 파일 fileNames에서 추출
      List<Map<String, String>> list = fileNames.stream().map(str -> {
        //_ 기준으로 문자열 자르기
        //String[] splitStr = str.split("_");
        //uuid 가져오기
        String uuid = str.substring(0, 36);
        //실제 파일명 가져오기
        String fileName = str.substring(37);
        //return map에 담기
        return Map.of("uuid", uuid, "fileName", fileName, "pno", "" + pno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());
      log.info(list);
      //파일 등록 실행
      fileMapper.createImage(list);
    }//end if
    log.info("============ //Product Update Service ============");
    return updateCount;
  }

  @Override
  public int viewCount(Long pno) {
    return productMapper.viewCount(pno);
  }
}
