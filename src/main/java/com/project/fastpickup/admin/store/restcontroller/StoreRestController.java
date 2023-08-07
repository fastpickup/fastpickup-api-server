package com.project.fastpickup.admin.store.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.store.dto.StoreDTO;
import com.project.fastpickup.admin.store.dto.StoreDTOForMember;
import com.project.fastpickup.admin.store.service.StoreService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/store/")
public class StoreRestController {

    // 의존성 주입
    private final StoreService storeService;

    // Autowired 명시적 표시
    @Autowired
    public StoreRestController(StoreService storeService) {
        log.info("Constructor Called, Service Injected.");
        this.storeService = storeService;
    }

    // Read Read Store Api
    @PreAuthorize("permitAll")
    @GetMapping("read/{sno}")
    public StoreDTO getReadStore(@PathVariable("sno") Long sno) {
        log.info("RestController | Api Store Read");
        StoreDTO result = storeService.readStore(sno);
        return result;
    }

    // List Store And CategoryName Api
    @PreAuthorize("permitAll")
    @GetMapping("list/{categoryName}")
    public ResponseEntity<Map<String, PageResponseDTO<StoreDTOForMember>>> getListStoreForCategoryName(@PathVariable("categoryName") String categoryName, PageRequestDTO pageRequestDTO) {
        log.info("RestController | Api Store List For CategoryName");
        PageResponseDTO<StoreDTOForMember> list = storeService.listStoreForCategory(categoryName, pageRequestDTO);
        return new ResponseEntity<>(Map.of("list", list), HttpStatus.OK);
    }
}
