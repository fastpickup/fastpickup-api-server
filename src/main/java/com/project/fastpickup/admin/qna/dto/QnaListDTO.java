package com.project.fastpickup.admin.qna.dto;

/*
 * Date   : 2023.08.07
 * Author : 송수정
 * E-mail : sujung033131@gmail.com
 */

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QnaListDTO {
    // 변수
    private Long qno; // 문의번호(PK)
    private String qnaTitle; // 문의제목
    private LocalDateTime registDate; // 등록일자
    private String email; // 이메일(FK)
    private int replyStatus; // 답변 여부
}
