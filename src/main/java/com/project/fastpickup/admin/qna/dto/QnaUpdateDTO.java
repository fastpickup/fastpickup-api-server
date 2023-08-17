package com.project.fastpickup.admin.qna.dto;

/*
 * Date   : 2023.08.07
 * Author : 송수정
 * E-mail : sujung033131@gmail.com
 */

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QnaUpdateDTO {
    // 변수
    private Long qno; // 문의번호
    private String qnaTitle; // 문의제목
    private String qnaContent; // 문의내용
}
