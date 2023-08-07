package com.project.fastpickup.admin.order.dto.kakao;

import lombok.Data;

@Data
public class AmountVO {
    private Integer total, tax_free, vat, point, discount;
}
