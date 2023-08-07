package com.project.fastpickup.admin.product.mappers;

/*
 * Date   : 2023.07.27
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import java.util.List;
import java.util.Map;

public interface FileMapper {
  //Create File
  int createImage(List<Map<String, String>> imageList);

  //Delete File
  int deleteImage(Long pno);
}
