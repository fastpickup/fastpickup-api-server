package com.project.fastpickup.admin.order.restcontroller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("permitAll")
    @PostMapping("/pay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");
        return kaKaoPay.kakaoPayReady(); // 여기서 카카오페이 결제 준비 페이지 URL을 반환합니다.
    }

    @PreAuthorize("permitAll")
    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        // 여기서 필요한 로직을 수행하고, 결과를 반환합니다.
        return "Success";
    }
}