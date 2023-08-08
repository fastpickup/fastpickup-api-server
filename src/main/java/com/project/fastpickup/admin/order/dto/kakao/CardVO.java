package com.project.fastpickup.admin.order.dto.kakao;

/*
 * Date   : 2023.08.07
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardVO {
    private String purchase_corp, purchase_corp_code;
    private String issuer_corp, issuer_corp_code;
    private String bin, card_type, install_month, approved_id, card_mid;
    private String interest_free_install, card_item_code;
}