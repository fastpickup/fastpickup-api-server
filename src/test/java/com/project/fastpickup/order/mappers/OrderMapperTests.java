package com.project.fastpickup.order.mappers;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

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
import com.project.fastpickup.admin.order.mappers.OrderHistoryMapper;
import com.project.fastpickup.admin.order.mappers.OrderMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class OrderMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private OrderHistoryMapper orderHistoryMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    // tbl_product
    private static final Long TEST_PNO = 22L;

    // tbl_member
    private static final String TEST_EMAIL = "thistrik@naver.com";

    private static final String TEST_EMAIL_VERSION_2 = "whtkdgml3627@naver.com";

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

    private OrderCreateDTO orderMemberCreateOrderDTO;
    private OrderHistoryCreateDTO orderHistoryCreateDTO;

    @BeforeEach
    public void setUp() {

        orderMemberCreateOrderDTO = OrderCreateDTO.builder()
                .orderCount(TEST_ORDER_COUNT)
                .email(TEST_EMAIL_VERSION_2)
                .sno(TEST_SNO)
                .pno(TEST_PNO)
                .build();

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

    @Test
    @Transactional
    @DisplayName("Mapper: 내 주문 이력 리스트")
    public void listOrderMyHistory() {
        log.info("=== Start List Order My History ===");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<OrderAndHistoryListDTO> list = orderMapper.listOrderMyHistory(TEST_EMAIL, pageRequestDTO);
        log.info(list);
        Assertions.assertNotNull(list, "list Should Not Be Null");
        log.info("=== Start List Order My History ===");
    }

    @Test
    @Transactional
    @DisplayName("Mapper: 내 주문 상세 이력")
    public void readOrderMyHistory() {
        log.info("=== Start Read My Order History ===");
        OrderDTO readMyOrderHistory = orderMapper.readOrderMyHistory(TEST_ONO);
        log.info(readMyOrderHistory);
        Assertions.assertNotNull(readMyOrderHistory, "readMyOrderHistory Should Be Not Null");
        log.info("=== End Read My Order History ===");
    }

    // Create Order And OrderHistory
    @Test
    @Transactional
    @DisplayName("주문과 주문이력 생성 매퍼 테스트")
    public void createOrderAndOrderHistoryMapper() {
        log.info("=== Start Create Order & History Mapper ===");
        Long createOrder = orderMapper.createOrder(orderCreateDTO);

        Long ono = orderCreateDTO.getOno();
        orderHistoryCreateDTO.setOno(ono);

        Long createOrderHistory = orderHistoryMapper.createHistory(orderHistoryCreateDTO);

        Assertions.assertEquals(TEST_EMAIL, "thistrik@naver.com");
        Assertions.assertEquals(TEST_ORDER_COUNT, 5L);
        Assertions.assertEquals(TEST_SNO, 1L);
        Assertions.assertEquals(TEST_PNO, 22L);
        log.info("=== End Create Order & History Mapper ===");
    }
}
