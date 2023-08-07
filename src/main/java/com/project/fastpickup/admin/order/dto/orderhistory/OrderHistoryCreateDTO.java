package com.project.fastpickup.admin.order.dto.orderhistory;

/*
 * Date   : 2023.07.28
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

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
public class OrderHistoryCreateDTO {
  // tbl_order_history
  private Long orderHistory; // 주문 이력 번호
  @Builder.Default
  private String orderStatus = "접수";
  private Long ono; // order 주문 번호
}
