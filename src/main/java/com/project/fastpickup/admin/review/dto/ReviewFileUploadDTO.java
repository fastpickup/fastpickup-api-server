package com.project.fastpickup.admin.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * Date   : 2023.08.04
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
 @ToString
public class ReviewFileUploadDTO {
    // 변수
    private String uuid; // PK
    private String fileName; // 실제파일명
    private boolean img; // 이미지 존재 유무

    // 이미지 파일 경로 가져오기
    public String getLink() {
        if (img) {
            return "s_" + uuid + "_" + fileName;
        } else {
            return "noImage.png";
        }
    }
}
