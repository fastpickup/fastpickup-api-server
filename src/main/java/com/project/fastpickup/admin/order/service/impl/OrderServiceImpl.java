package com.project.fastpickup.admin.order.service.impl;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryCreateDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryDTO;
import com.project.fastpickup.admin.order.exception.OrderNotFoundException;
import com.project.fastpickup.admin.order.mappers.OrderHistoryMapper;
import com.project.fastpickup.admin.order.mappers.OrderMapper;
import com.project.fastpickup.admin.order.service.OrderService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

// Order ServiceImpl Class
@Log4j2
@Service
public class OrderServiceImpl implements OrderService {

    // 의존성 주입
    private final OrderHistoryMapper orderHistoryMapper;
    private final OrderMapper orderMapper;

    // Autowired 명시적 표시
    @Autowired
    public OrderServiceImpl(OrderHistoryMapper orderHistoryMapper, OrderMapper orderMapper) {
        log.info("Constructor Called, Mapper Injected.");
        this.orderHistoryMapper = orderHistoryMapper;
        this.orderMapper = orderMapper;
    }

    // Create Order And OrderHistory ServiceImpl
    @Override
    @Transactional
    public Long createOrder(OrderCreateDTO orderCreateDTO) {
        log.info("Is Running Create Order ServiceImpl");
        orderMapper.createOrder(orderCreateDTO);
        Long ono = orderCreateDTO.getOno();
        OrderHistoryCreateDTO orderHistoryCreateDTO = OrderHistoryCreateDTO.builder().ono(ono).build();
        return orderHistoryMapper.createHistory(orderHistoryCreateDTO);
    }

    // Read Order And OrderHistory And Product And ProductImage And Store
    // ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public OrderDTO readOrder(Long ono) {
        log.info("Is Running Read Order And EveryThing ServiceImpl");
        return orderMapper.readOrder(ono);
    }

    // Read History ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public OrderHistoryDTO readHistory(Long ono) {
        log.info("Is Running Read History Serviceimpl");
        return orderHistoryMapper.readHistory(ono);
    }

    // Check Order Number ServiceImpl
    @Override
    @Transactional
    public void checkOrderNumber(Long ono) {
        log.info("Is Running Order Number ServiceImpl");
        if (orderMapper.duplicateOno(ono) == 0) {
            throw new OrderNotFoundException("해당하는 주문 번호가 없습니다.");
        }
    }

    // List My Order History ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<OrderAndHistoryListDTO> listMyOrderHistory(String email, PageRequestDTO pageRequestDTO) {
        log.info("Is Running List My Order History ServiceImpl");
        List<OrderAndHistoryListDTO> list = orderMapper.listOrderMyHistory(email, pageRequestDTO);
        int total = orderMapper.total(pageRequestDTO);
        return PageResponseDTO.<OrderAndHistoryListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // Read My Order History ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public OrderDTO readMyOrderHistory(Long ono) {
        log.info("Is Running Read My Order History ServiceImpl");
        return orderMapper.readOrderMyHistory(ono);
    }
}
