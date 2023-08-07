package com.project.fastpickup.admin.like.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.like.dto.LikeDTO;
import com.project.fastpickup.admin.like.service.LikeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/like")
public class LikeRestController {

    private final LikeService likeService;

    // Autowired 명시적 표시
    @Autowired
    public LikeRestController(LikeService likeService) {
        log.info("Constructor Called, Like Service Injected.");
        this.likeService = likeService;
    }

    // Post Like Toggle API
    @PreAuthorize("permitAll")
    @PostMapping("/pno/toggle/{pno}/{email}")
    public ResponseEntity<Map<String, Integer>> toggleLike(@PathVariable("pno") Long pno,
            @PathVariable("email") String email) {
        log.info("Restcontroller | Api Toggle Like");
        int result = likeService.toggleLike(pno, email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Get Like Count API
    @PreAuthorize("permitAll")
    @GetMapping("/pno/{pno}/count")
    public ResponseEntity<Map<String, Long>> countLike(@PathVariable("pno") Long pno) {
        log.info("RestController | Api Count Like");
        Long result = likeService.countLike(pno);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Check Like
    @PreAuthorize("permitAll")
    @GetMapping("/pno/{pno}/{email}/check")
    public ResponseEntity<Map<String, Boolean>> checkLike(@PathVariable("pno") Long pno,
            @PathVariable("email") String email) {
        log.info("RestController | Api Check Like");
        LikeDTO result = likeService.checkLikeByMemberAndPost(pno, email);
        return new ResponseEntity<>(Map.of("liked", result != null), HttpStatus.OK);
    }
}
