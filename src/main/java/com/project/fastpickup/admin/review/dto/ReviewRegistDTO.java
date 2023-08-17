package com.project.fastpickup.admin.review.dto;

/*
 * Date   : 2023.08.04
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

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

    @Builder.Default
    private Long gno = 0L;
    
    private Long sno;
    private Long ono;
    private String email;
    private String reviewTitle;
    private String reviewContent;

    @Builder.Default
    private List<String> fileNames = new ArrayList<>(); // 파일명

}
