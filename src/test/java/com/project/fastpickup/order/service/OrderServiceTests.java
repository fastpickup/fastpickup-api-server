package com.project.fastpickup.order.service;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.order.dto.OrderAndHistoryListDTO;
import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import com.project.fastpickup.admin.order.dto.order.OrderDTO;
import com.project.fastpickup.admin.order.dto.orderhistory.OrderHistoryCreateDTO;
import com.project.fastpickup.admin.order.service.OrderService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class OrderServiceTests {

    // 의존성 주입
    @Autowired
    private OrderService orderService;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    // tbl_product
    private static final Long TEST_PNO = 22L;

    // tbl_member
    private static final String TEST_EMAIL = "thistrik@naver.com";

    // tbl_store
    private static final Long TEST_SNO = 1L;

    // tbl_order
    private static final Long TEST_ONO = 4L;
    private static final int TEST_ORDER_COUNT = 5;
    private static final int TEST_ORDER_COUNT_VERSION_2 = 7;
    private static final String TEST_ORDER_STATUS_REJECT = "반려";
    private static final String TEST_ORDER_STATUS_COMPLETE = "완료";
    private static final String TEST_ORDER_STATUS_CONFIRM = "주문확인";

    // tbl_order_history
    private static final Long TEST_ORDER_HISTORY = 4L;

    // DTO 정의
    private OrderCreateDTO orderCreateDTO;
    private OrderHistoryCreateDTO orderHistoryCreateDTO;

    @BeforeEach
    public void setUp() {
        orderCreateDTO = OrderCreateDTO.builder()
                .orderCount(TEST_ORDER_COUNT)
                .email(TEST_EMAIL)
                .sno(TEST_SNO)
                .pno(TEST_PNO)
                .build();

        orderHistoryCreateDTO = OrderHistoryCreateDTO.builder()
                .orderStatus(TEST_ORDER_STATUS_REJECT)
                .build();
    }

    // Create Order And OrderHistory
    @Test
    @Transactional
    @DisplayName("Service: 주문과 주문이력 생성")
    public void createOrderAndOrderHistory() {
        // GIVEN
        log.info("=== Start Create Order & History Service ===");
        // WHEN
        Long createOrder = orderService.createOrder(orderCreateDTO);
        // THEN
        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_ORDER_COUNT, 5L);
        Assertions.assertEquals(TEST_SNO, 1L);
        Assertions.assertEquals(TEST_PNO, 22L);
        log.info("=== End Create Order & History Service ===");
    }

    // Read My Order History
    @Test
    @Transactional
    @DisplayName("Service: 주문 이력 상세조회")
    public void readMyOrderHistory() {
        // GIVEN 
        log.info("=== Start Read My Order History ===");
        // WHEN 
        OrderDTO readMyOrderHistory = orderService.readMyOrderHistory(TEST_ONO);
        // THEN 
        log.info(readMyOrderHistory);
        Assertions.assertNotNull(readMyOrderHistory, "readMyOrderHistory Should Be Not Null");
        log.info("=== End Read My Order History ===");
    }

    // List My Order History
    @Test
    @Transactional
    @DisplayName("Service: 주문 이력 리스트")
    public void listMyOrderHistory() {
        // GIVEN 
        log.info("=== Start List My Order History ===");
        // WHEN 
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<OrderAndHistoryListDTO> list = orderService.listMyOrderHistory(TEST_EMAIL, pageRequestDTO);
        // THEN 
        log.info(list.getList());
        Assertions.assertNotNull(list.getList(), "list.getList() Should Be Not Null");
        log.info("=== End List My Order History ===");
    }
}
