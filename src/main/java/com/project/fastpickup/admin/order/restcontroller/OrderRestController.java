package com.project.fastpickup.admin.order.restcontroller;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.service.OrderService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    // 의존성 주입
    private final OrderService orderService;

    // Autowired 명시적 표시
    @Autowired
    public OrderRestController(OrderService orderService) {
        log.info("Constructor Called, Service Injected.");
        this.orderService = orderService;
    }

    // List My Order History Api
    @PreAuthorize("permitAll")
    @GetMapping("list/{email}")
    public ResponseEntity<Map<String, PageResponseDTO<OrderAndHistoryListDTO>>> getListMyOrderHistory(
            @PathVariable("email") String email, PageRequestDTO pageRequestDTO) {
        log.info("RestController | Api List My Order");
        PageResponseDTO<OrderAndHistoryListDTO> list = orderService.listMyOrderHistory(email, pageRequestDTO);
        return new ResponseEntity<>(Map.of("list", list), HttpStatus.OK);
    }

    // Read My Order History Api
    @PreAuthorize("permitAll")
    @GetMapping("read/{ono}")
    public ResponseEntity<Map<String, OrderDTO>> getReadOrderHistory(@PathVariable("ono") Long ono) {
        log.info("RestController | Api Read My Order");
        OrderDTO result = orderService.readMyOrderHistory(ono);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Create Order And Order History Api
    @PreAuthorize("permitAll")
    @PostMapping("create")
    public ResponseEntity<Map<String, Long>> postCreateOrderAndOrderHistory(OrderCreateDTO orderCreateDTO) {
        log.info("RestController | Api Create My Order And Order History");
        Long result = orderService.createOrder(orderCreateDTO);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }
}
