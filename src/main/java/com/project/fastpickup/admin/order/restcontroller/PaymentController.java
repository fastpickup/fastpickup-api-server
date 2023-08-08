package com.project.fastpickup.admin.order.restcontroller;

/*
 * Date   : 2023.08.07
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.order.dto.order.OrderCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.project.fastpickup.admin.order.dto.kakao.KakaoPayApprovalV0;
import com.project.fastpickup.admin.order.service.impl.KaKaoPay;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/kakaoPay")
public class PaymentController {

    private final KaKaoPay kaKaoPay;

    @Autowired
    public PaymentController(KaKaoPay kaKaoPay) {
        this.kaKaoPay = kaKaoPay;
    }

    // POST | KakaoPay Ready
    @PostMapping("/pay")
    public String kakaoPay(
      @RequestParam("pno") Long pno, @RequestParam("total") String total, @RequestParam("email") String email, @RequestParam("sno") Long sno, @RequestParam("orderCount") int orderCount
    ) {
        log.info("----------------------------------------------------------");
        log.info("kakaoPay post............................................");

        return kaKaoPay.kakaoPayReady(pno, total, email, sno); // 여기서 카카오페이 결제 준비 페이지 URL을 반환합니다.
    }

    // GET | KakaoPay Success
    @GetMapping("/kakaoPaySuccess")
    public RedirectView kakaoPaySuccess(
      @RequestParam("pg_token") String pg_token, @RequestParam("pno") Long pno, @RequestParam("total") String total, @RequestParam("email") String email, @RequestParam("sno") Long sno, @RequestParam("orderCount") int orderCount
    ) {
        KakaoPayApprovalV0 approval = kaKaoPay.kakaoPayInfo(pg_token, pno, total, email, sno);

        OrderCreateDTO orderCreateDTO = OrderCreateDTO.builder()
          .pno(pno)
          .sno(sno)
          .email(email)
          .build();

        // 여기서 필요한 로직을 수행하고, 결과를 반환합니다.
        // 예를 들어, 결제가 성공적으로 이루어졌는지 확인, 데이터베이스에 기록 등의 작업을 수행할 수 있습니다.
        log.info("----------------------------------------------------------");
        log.info("order create............................................");
        log.info(orderCreateDTO);

        // 리액트 측으로 리다이렉트 URL 지정
        String redirectUrl = "http://localhost:3000/order/complete"; // 여기에 원하는 URL을 지정합니다.

        return new RedirectView(redirectUrl);
    }
}