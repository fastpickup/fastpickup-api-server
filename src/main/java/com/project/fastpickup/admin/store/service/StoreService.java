package com.project.fastpickup.admin.store.service;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

// Store Service Interface 
public interface StoreService {

    // Read Store
    StoreDTO readStore(Long sno);

    // Check Store Number
    void checkStoreNumber(Long sno);

    PageResponseDTO<StoreDTOForMember> listStoreForCategory(String categoryName, PageRequestDTO pageRequestDTO);
}
