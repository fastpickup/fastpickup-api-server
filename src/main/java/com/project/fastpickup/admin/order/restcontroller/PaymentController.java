package com.project.fastpickup.admin.order.restcontroller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/pay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return kaKaoPay.kakaoPayReady(); // 여기서 카카오페이 결제 준비 페이지 URL을 반환합니다.
    }

    @GetMapping("/kakaoPaySuccess")
    public RedirectView kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        KakaoPayApprovalV0 approval = kaKaoPay.kakaoPayInfo(pg_token);

        // 여기서 필요한 로직을 수행하고, 결과를 반환합니다.
        // 예를 들어, 결제가 성공적으로 이루어졌는지 확인, 데이터베이스에 기록 등의 작업을 수행할 수 있습니다.

        // 리액트 측으로 리다이렉트 URL 지정
        String redirectUrl = "http://localhost:3000/member/mypage"; // 여기에 원하는 URL을 지정합니다.

        return new RedirectView(redirectUrl);
    }
}