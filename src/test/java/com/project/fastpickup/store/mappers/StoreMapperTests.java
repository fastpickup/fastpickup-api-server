package com.project.fastpickup.store.mappers;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.store.mappers.StoreMapper;
import com.project.fastpickup.admin.util.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

// Store Mapper Test Class
@Log4j2
@SpringBootTest
public class StoreMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private StoreMapper storeMapper;

    // Test 시작시 메모리에 private static final 로 먼저 올려놓는다
    private static final String TEST_STORE_NAME = "교촌치킨";
    private static final String TEST_STORE_NUMBER = "12342-23423-2342";
    private static final String TEST_STORE_ADDRESS = "경기도 성남시";
    private static final String TEST_EMAIL = "thistrik@naver.com";
    private static final String TEST_STORE_PHONE = "12321-23423-523532";
    private static final Long TEST_SNO = 1L;
    private static final String TEST_CATEGORY_NAME = "치킨";

    // Read Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 상세 조회 테스트")
    public void readStoreMapperTest() {
        // GIVEN
        log.info("=== Start Read Store Mapper Test ===");
        // WHEN
        StoreDTO readStore = storeMapper.readStore(TEST_SNO);
        log.info("가맹점: " + readStore);
        Assertions.assertNotNull(readStore, "readStore Should Be Not Null");
        log.info("=== End Read Store Mapper Test ===");
    }

    // Total Store Mapper Test
    @Test
    @Transactional
    @DisplayName("가맹점 토탈 테스트")
    public void totalStoreMapperTest() {
        // GIVEN
        log.info("=== Start Total Store Mapper Test ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        int totalCount = storeMapper.total(pageRequestDTO);
        // THEN
        log.info(totalCount);
        Assertions.assertNotNull(totalCount, "totalCount Should Be Not Null");
        log.info("=== End Total Store Mapper Test ===");
    }

    // Read Store For Member
    @Test
    @Transactional
    @DisplayName("Mapper: 카테고리 가맹점 리스트")
    public void readStoreForMember() {
        // GIVEN
        log.info("=== Start Read Store For Member ===");
        // WHEN
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        List<StoreDTOForMember> list = storeMapper.listStoreForCategory(TEST_CATEGORY_NAME, pageRequestDTO);
        log.info(list);
        // THEN
        Assertions.assertNotNull(list, "list Should Be Not Nul");
        log.info("=== End Read Store For Member ===");
    }
}