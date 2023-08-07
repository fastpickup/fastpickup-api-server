package com.project.fastpickup.admin.store.mappers;

import java.util.List;

/*
 * Date   : 2023.07.27
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.util.PageRequestDTO;

// Store Mapper Interface
@Mapper
public interface StoreMapper {

    // Read Store
    StoreDTO readStore(Long sno);

    // Total Store
    int total(PageRequestDTO pageRequestDTO);

    // duplicate Sno
    int duplicateSno(Long sno);

    // List Store For Category
    List<StoreDTOForMember> listStoreForCategory(@Param("categoryName") String categoryName, @Param("pr") PageRequestDTO pageRequestDTO);

}
