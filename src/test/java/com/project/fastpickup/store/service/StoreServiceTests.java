package com.project.fastpickup.store.service;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class StoreServiceTests {

    // 의존성 주입
    @Autowired
    private StoreService storeService;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final String TEST_STORE_NAME = "교촌치킨";
    private static final String TEST_STORE_NUMBER = "12342-23423-2342";
    private static final String TEST_STORE_ADDRESS = "경기도 성남시";
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final Long TEST_SNO = 1L;
    private static final String TEST_STORE_PHONE = "2342-323-423";
    private static final String TEST_CATEGORY_NAME = "치킨";

    // Read Store Service
    @Test
    @Transactional
    @DisplayName("가맹점 상세 정보 테스트 서비스")
    public void readStoreServiceTest() {
        // GIVEN
        log.info("=== Start Read Store Service Test ===");
        // WHEN
        StoreDTO readStore = storeService.readStore(TEST_SNO);
        // THEN
        Assertions.assertNotNull(readStore, "readStore Should Be Not Null");
        log.info("=== End Read Store Service Test ===");
    }

    // List Store For CategoryName
    @Test
    @Transactional
    @DisplayName("Service: 가맹점 카테고리 리스트")
    public void listStoreForCategory() {
        // GIVEN
        log.info("=== Start List Store For Category ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<StoreDTOForMember> listEveryThing = storeService.listStoreForCategory(TEST_CATEGORY_NAME,
                pageRequestDTO);
        // THEN
        log.info(listEveryThing.getList());
        Assertions.assertNotNull(listEveryThing, "listEveryThing Should Be Not Null");
        log.info("=== End List Store For Category ===");
    }
}
