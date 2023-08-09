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
public class KakaoPayApprovalV0 {
    private String aid, tid, cid, sid;
    private String partner_order_id, partner_user_id, payment_method_type;
    private AmountVO amount;
    private CardVO card_info;
    private String item_name, item_code, payload;
    private Integer quantity, tax_free_amount, vat_amount;
    private Date created_at, approved_at;
}