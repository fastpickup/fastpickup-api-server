package com.project.fastpickup.admin.qna.dto.reply;

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
@AllArgsConstructor
@NoArgsConstructor
public class QnaReplyDTO {

    // 변수
    private Long rno; // 댓글번호(PK)
    private long qno; // 문의글 번호(FK)
    private String email; // 이메일(FK)
    private String reply; // 답글 내용
    private LocalDateTime replyDate; // 답글 등록일자
}
