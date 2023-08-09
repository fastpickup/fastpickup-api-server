package com.project.fastpickup.admin.review.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.fastpickup.admin.review.dto.ReviewFileUploadDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/files/")
public class ReviewFileRestController {

  // import 시에 springframework으로 시작하는 Value
  @Value("${com.project.upload.path}")
  private String uploadPath;

  //file upload
  @PreAuthorize("permitAll")
  @PostMapping("upload")
  public List<ReviewFileUploadDTO> upload(MultipartFile[] files){
    //파일 없을 경우
    if(files == null || files.length == 0){
      return null;
    }

    List<ReviewFileUploadDTO> fileList = new ArrayList<>();

    //파일 여러개 등록 하니 for each 사용
    for (MultipartFile file : files) {
      //초기값 null
      ReviewFileUploadDTO result = null;
      //실제 파일명
      String fileName = file.getOriginalFilename();
      //파일 크기
      long size = file.getSize();

      //uuid(pk) 생성
      String uuidStr = UUID.randomUUID().toString();
      //파일명 저장
      String saveFileName = uuidStr + "_" + fileName;
      //실제 파일 저장
      File saveFile = new File(uploadPath, saveFileName);

      //파일 복사할 때 예외처리 필요
      try {
        //FileCopyUtils 사용하여 InputStream으로 받고 OutPutStream으로 보내줌
        //getBytes() 파일을 바이트 배열로 받아줌
        FileCopyUtils.copy(file.getBytes(), saveFile);

        //dto에 넣어줌
        result = ReviewFileUploadDTO.builder()
          .uuid(uuidStr)
          .fileName(fileName)
          .build();

        //파일 확장자 이미지 파일 체크
        String mimeType = Files.probeContentType(saveFile.toPath());
        log.info("mimeType: " + mimeType);

        //mimeType이 image인지 체크
        if(mimeType.startsWith("image")){
          //s_ 썸네일 파일 생성
          File thumbFile = new File(uploadPath, "s_" + saveFileName);
          Thumbnailator.createThumbnail(saveFile, thumbFile, 80, 80);
          //img true로 반환 getLkin사용 위함
          result.setImg(true);
        }

        //리스트에 파일들 담아주기
        fileList.add(result);

      } catch (IOException e) {
        e.printStackTrace();
      }

    }//end for

    return fileList;
  }

  //file remove
  @PreAuthorize("permitAll")
  @DeleteMapping("remove/{fileName}")
  public Map<String, String> removeFile(
    @PathVariable("fileName") String fileName
  ){
    log.info("File Remove...........................................");
    log.info(fileName);

    File originFile = new File(uploadPath, fileName);

    try {

      String mimeType = Files.probeContentType(originFile.toPath());
      if(mimeType.startsWith("image")){
        File thumbFile = new File(uploadPath,"s_"+fileName);
        thumbFile.delete();
      }
      originFile.delete();

    } catch (IOException e) {
      e.printStackTrace();
    }

    return Map.of("result", "success");
  }



}
