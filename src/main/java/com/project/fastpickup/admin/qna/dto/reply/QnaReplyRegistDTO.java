package com.project.fastpickup.admin.qna.dto.reply;

/*
 * Date   : 2023.08.04
 * Author : 송수정
 * Author : 이범수
 * E-mail : sujung033131@gmail.com
 * E-mail : beomsu_1221@naver.com
 */

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
