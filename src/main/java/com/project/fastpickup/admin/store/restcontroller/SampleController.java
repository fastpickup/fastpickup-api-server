package com.project.fastpickup.admin.store.restcontroller;

import ch.qos.logback.core.model.Model;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.fastpickup.admin.order.service.impl.KaKaoPay;

import lombok.extern.java.Log;

@Log
@Controller

public class SampleController {

    @Autowired
    private HttpSession session;

    @Setter(onMethod_ = @Autowired)
    private KaKaoPay kakaopay;

    @PreAuthorize("permitAll")
    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("kakaoPay post............................................");

        return "redirect:" + kakaopay.kakaoPayReady();

    }

    @PreAuthorize("permitAll")
    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

    }
}
