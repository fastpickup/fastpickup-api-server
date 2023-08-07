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
public class ReviewRegistDTO {

    private Long rno;
    private Long sno;
    private Long ono;
    private String email;
    private String reviewTitle;
    private String reviewContent;

    @Builder.Default
    private List<String> fileNames = new ArrayList<>(); // 파일명

}
