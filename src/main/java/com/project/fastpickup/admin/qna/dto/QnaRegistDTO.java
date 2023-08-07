package com.project.fastpickup.admin.qna.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QnaRegistDTO {
    // 변수
    private Long qno;
    private String qnaTitle; // 문의제목
    private String qnaContent; // 문의내용
    private String email; // 이메일(FK)
}
