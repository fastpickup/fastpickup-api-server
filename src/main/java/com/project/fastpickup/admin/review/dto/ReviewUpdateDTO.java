package com.project.fastpickup.admin.review.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewUpdateDTO {

    private Long rno;                                   // 리뷰 번호
    private String reviewTitle;                         // 리뷰 제목
    private String reviewContent;                       // 리뷰 내용
    @Builder.Default    
    private List<String> fileNames = new ArrayList<>(); // 파일명

}
