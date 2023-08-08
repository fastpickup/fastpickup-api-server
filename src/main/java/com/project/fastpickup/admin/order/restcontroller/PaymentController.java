package com.project.fastpickup.admin.order.restcontroller;

/*
 * Date   : 2023.08.07
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public String kakaoPay() {
        log.info("RestController | KakaoPay");
        return kaKaoPay.kakaoPayReady(); // 여기서 카카오페이 결제 준비 페이지 URL을 반환합니다.
    }

    // GET | KakaoPay Success
    @GetMapping("/kakaoPaySuccess")
    public RedirectView kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        KakaoPayApprovalV0 approval = kaKaoPay.kakaoPayInfo(pg_token);
        String redirectUrl = "http://localhost:3000/member/mypage";
        return new RedirectView(redirectUrl);
    }
}