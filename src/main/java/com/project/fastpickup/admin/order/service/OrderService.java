package com.project.fastpickup.admin.order.service;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

// Order Service Interface
public interface OrderService {

    // Create Order
    Long createOrder(OrderCreateDTO orderCreateDTO);

    // Read Order
    OrderDTO readOrder(Long ono);

    // Read Order History
    OrderHistoryDTO readHistory(Long ono);

    // Check Order Number
    void checkOrderNumber(Long ono);

    // 내 주문 이력 리스트
    PageResponseDTO<OrderAndHistoryListDTO> listMyOrderHistory(String email, PageRequestDTO pageRequestDTO);

    // 내 주문 상세
    OrderDTO readMyOrderHistory(Long ono);
}
