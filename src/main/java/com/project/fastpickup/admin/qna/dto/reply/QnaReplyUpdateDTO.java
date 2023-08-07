package com.project.fastpickup.admin.qna.dto.reply;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QnaReplyUpdateDTO {

    // 변수
    private Long rno; // 답글 번호(PK)
    private long qno; // 문의글 번호(FK)
    private String reply; // 답글 내용


}
