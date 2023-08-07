package com.project.fastpickup.admin.qna.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    // 변수
    private Long qno; // 문의번호(PK)
    private String qnaTitle; // 문의제목
    private String qnaContent; // 문의내용
    private LocalDateTime registDate; // 등록일자
    private LocalDateTime updateDate; // 수정일자
    private String email; // 이메일(FK)
}
