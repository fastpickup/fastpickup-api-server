package com.project.fastpickup.admin.order.dto.order;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long ono; // 주문 번호
    private LocalDateTime registDate; // 등록 일자
    @Builder.Default
    private int orderCount = 1; // 주문 수량
    private String email; // 사용자 이메일
    private Long sno; // 가맹정 번호
    private Long pno; // 상품 번호
    private Long totalPrice;

    // tbl_order_history
    private Long orderHistory; // 주문 이력 번호
    @Builder.Default
    private String orderStatus = "접수";

    // tbl_product
    private String productName; // 상품명
    private int productPrice; // 상품가격
    
    private String fileName;

    // tbl_store
    private String storeName;
    private String storeAddress;
    private String storePhone;
    
    // tbl_member
    private String memberName;
    
}
