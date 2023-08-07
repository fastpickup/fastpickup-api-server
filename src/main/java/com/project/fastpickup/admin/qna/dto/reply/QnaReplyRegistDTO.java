package com.project.fastpickup.admin.qna.dto.reply;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QnaReplyRegistDTO {

    // 변수
    private long qno; // 문의 번호
    private String reply; // 답글 내용
    private String email; // 이메일

}
