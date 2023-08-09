package com.project.fastpickup.admin.order.dto.kakao;

/*
 * Date   : 2023.08.07
 * Author : 권성준
 * Author : 조상희
 * E-mail : thistrik@naver.com
 * E-mail : jo_sh_1028@naver.com
 */

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoPayReadyVO {
    private String tid, next_redirect_pc_url;
    private Date created_at;
}