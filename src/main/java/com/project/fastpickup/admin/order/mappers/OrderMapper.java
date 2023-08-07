package com.project.fastpickup.admin.order.mappers;

import java.util.List;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

// Order Mapper Interface 
@Mapper
public interface OrderMapper {

    /// Create Order
    Long createOrder(OrderCreateDTO orderCreateDTO);

    // Read Order
    OrderDTO readOrder(Long ono);

    // Total
    int total(PageRequestDTO pageRequestDTO);

    // Duplicate Ono
    int duplicateOno(Long ono);

    // 내 주문 리스트 Mapper
    List<OrderAndHistoryListDTO> listOrderMyHistory(@Param("email") String email,
            @Param("pr") PageRequestDTO pageRequestDTO);

    // 내 주문 상세 Mapper
    OrderDTO readOrderMyHistory(Long ono);
}
