package com.project.fastpickup.admin.kakaomap.controller;

/*
 * Date   : 2023.08.14
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Kakao Map Controller Class
@Log4j2
@RestController
@RequestMapping("/api/map")
public class KakaoMapController {

    // Kakao Java Script Key 
    @Value("${kakao.api.key}")
    private String javaScriptKey;

    // Kakao Rest API Key 
    @Value("${kakao.rest.api.key}")
    private String restApiKey;

    // Kakao Map의 위도와 경도를 보내줍니다 
    @GetMapping("/getMapData")
    public ResponseEntity<String> getMapData(@RequestParam String query) {
        log.info("RestController | API Get Kakao Map");
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dapi.kakao.com/v2/local/search/address.json?query="
                            + URLEncoder.encode(query, "UTF-8"))
                    .addHeader("Authorization", "KakaoAK " + restApiKey)
                    .build();

            Response response = client.newCall(request).execute();
            log.info("response카카오: "+response);
            return ResponseEntity.ok(response.body().string());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch Kakao data");
        }
    }

    // SDK URL 을 보내줍니다 
    @GetMapping("/getKakaoSdkUrl")
    public ResponseEntity<String> getKakaoSdkUrl() {
        log.info("RestController | API Get KAKAOSDK");
        String kakaoSdkUrl = "//dapi.kakao.com/v2/maps/sdk.js?appkey=" + javaScriptKey;
        log.info("kakaoSdkUrl: " +kakaoSdkUrl);
        return ResponseEntity.ok(kakaoSdkUrl);
    }
}
