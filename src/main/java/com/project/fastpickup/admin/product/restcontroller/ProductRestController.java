package com.project.fastpickup.admin.product.restcontroller;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/product/")
public class ProductRestController {
  //의존성 주입
  private final ProductService productService;

  //List Category
  @GetMapping("category")
  @PreAuthorize("permitAll")
  public ResponseEntity<Map<String, List<ProductCategoryDTO>>> getCateList(){
    log.info("get REST | Get Cate List =================");
    List<ProductCategoryDTO> list = productService.getCateList();
    return new ResponseEntity<>(Map.of("list", list), HttpStatus.OK);
  }

  //List Store Prodcut
  @GetMapping("{sno}/list")
  @PreAuthorize("permitAll")
  public ResponseEntity<Map<String, PageResponseDTO<ProductListDTO>>> getProductList(
    @PathVariable("sno") Long sno, PageRequestDTO pageRequestDTO
  ){
    log.info("get REST | Get Product List =================");
    PageResponseDTO<ProductListDTO> list = productService.getStoreList(pageRequestDTO, sno);
    return new ResponseEntity<>(Map.of("list", list), HttpStatus.OK);
  }

  //Read Product
  @GetMapping("read/{pno}")
  @PreAuthorize("permitAll")
  public ResponseEntity<Map<String, ProductDTO>> getRead(
    @PathVariable("pno") Long pno
  ){
    log.info("get REST | Get Product Read =================");
    ProductDTO dto = productService.selectOne(pno);
    return new ResponseEntity<>(Map.of("dto", dto), HttpStatus.OK);
  }

    
}
