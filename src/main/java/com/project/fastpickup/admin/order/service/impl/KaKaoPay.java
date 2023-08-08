package com.project.fastpickup.admin.order.service.impl;

/*
 * Date   : 2023.08.07
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.net.URI;
import java.net.URISyntaxException;

import com.project.fastpickup.admin.product.dto.ProductDTO;
import com.project.fastpickup.admin.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.fastpickup.admin.order.dto.kakao.KakaoPayApprovalV0;
import com.project.fastpickup.admin.order.dto.kakao.KakaoPayReadyVO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class KaKaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalV0 kakaoPayApprovalV0;

    @Autowired
    private ProductService productService;

    // 카카오페이 결제 준비 메소드
    // 카카오페이와의 연결을 준비하고, 결제를 위한 요청을 보낸다.
    public String kakaoPayReady(Long pno, String total, String email, Long sno) {
        RestTemplate restTemplate = new RestTemplate();

        ProductDTO product = productService.selectOne(pno);

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9e31945e73bd535f37a14637e5de6938");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        // 결제 준비 요청을 처리하고, 결제 페이지로 리디렉션할 URL을 반환한다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", product.getProductName());
        params.add("quantity", "1");
        params.add("total_amount", total);
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8081/kakaoPay/kakaoPaySuccess?pno="+pno+"&total="+total+"&email="+email+"&sno="+sno);
        params.add("cancel_url", "http://localhost:8081/kakaoPay/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8081/kakaoPay/kakaoPaySuccessFail");
        //params.add("approval_url", "http://localhost:3000/order/complete?pno="+pno);
        //params.add("cancel_url", "http://localhost:3000/kakaoPayCancel");
        //params.add("fail_url", "http://localhost:3000/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,
                    KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";
    }

    // 카카오페이 결제 승인 메소드
    // 사용자가 카카오페이를 통해 결제를 완료한 후 호출되며, 카카오페이와의 결제를 최종 승인한다.
    public KakaoPayApprovalV0 kakaoPayInfo(String pg_token, Long pno, String total, String email, Long sno) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "9e31945e73bd535f37a14637e5de6938");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", total);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalV0 = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalV0.class);
            log.info("" + kakaoPayApprovalV0);
            // 결제 승인 요청을 처리하고, 결제 승인 결과를 반환한다.
            return kakaoPayApprovalV0;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
