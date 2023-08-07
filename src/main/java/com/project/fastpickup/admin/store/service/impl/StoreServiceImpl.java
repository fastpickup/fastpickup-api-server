package com.project.fastpickup.admin.store.service.impl;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.store.exception.StoreNotFoundException;
import com.project.fastpickup.admin.store.mappers.StoreMapper;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

// Store ServiceImpl Class
@Log4j2
@Service
public class StoreServiceImpl implements StoreService {

    // 의존성 주입
    private final StoreMapper storeMapper;

    // Autowired 명시적 표시
    @Autowired
    public StoreServiceImpl(StoreMapper storeMapper) {
        log.info("Constructor Called, Mapper Injected.");
        this.storeMapper = storeMapper;
    }

    // Read Store ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public StoreDTO readStore(Long sno) {
        log.info("Is Running Read Store ServiceImpl");
        return storeMapper.readStore(sno);
    }

    // Check Store Number ServiceImpl
    @Override
    @Transactional
    public void checkStoreNumber(Long sno) {
        log.info("Is Running Check Store Number");
        if (storeMapper.duplicateSno(sno) == 0) {
            throw new StoreNotFoundException("찾으시는 가맹점이 없습니다.");
        }
    }

    // List Store For Category ServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<StoreDTOForMember> listStoreForCategory(String categoryName, PageRequestDTO pageRequestDTO) {
        log.info("Is Running List Store For Category ServiceImpl");
        List<StoreDTOForMember> list = storeMapper.listStoreForCategory(categoryName, pageRequestDTO);
        int total = storeMapper.total(pageRequestDTO);
        return PageResponseDTO.<StoreDTOForMember>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

}
