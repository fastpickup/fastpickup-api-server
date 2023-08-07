package com.project.fastpickup.admin.member.restcontroller;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

@Log4j2
@RestController
@RequestMapping("/api/member/")
public class MemberRestController {

    // 의존성 주입
    private final MemberService memberService;

    // Autowired 명시적 표시
    @Autowired
    public MemberRestController(MemberService memberService) {
        log.info("Constructor Called, Member Service Injected.");
        this.memberService = memberService;
    }

    // Get Member Read API
    @PreAuthorize("permitAll")
    @GetMapping("read/{email}")
    public ResponseEntity<Map<String, MemberConvertDTO>> getReadMember(@PathVariable("email") String email) {
        log.info("RestController | Api Get Member Read");
        MemberConvertDTO member = memberService.readMember(email);
        return new ResponseEntity<>(Map.of("member", member), HttpStatus.OK);
    }

    // Join Member API
    @PreAuthorize("permitAll")
    @PostMapping("create")
    public ResponseEntity<Map<String, Integer>> postJoinMember(@RequestBody MemberConvertDTO memberConvertDTO) {
        log.info("RestController | Api Post Create Member");
        int result = memberService.joinMember(memberConvertDTO);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Delete Member API
    @PreAuthorize("permitAll")
    @DeleteMapping("delete/{email}")
    public ResponseEntity<Map<String, Integer>> postDeleteMember(@PathVariable("email") String email) {
        log.info("RestController | Api Delete Member");
        int result = memberService.deleteMember(email);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }

    // Get Member Update API
    @PreAuthorize("permitAll")
    @GetMapping("update/{email}")
    public ResponseEntity<Map<String, MemberConvertDTO>> getUpdateMember(@PathVariable("email") String email) {
        log.info("RestController | Api Get Update Member");
        MemberConvertDTO member = memberService.readMember(email);
        log.info("회원정보" + member);
        return new ResponseEntity<>(Map.of("member", member), HttpStatus.OK);
    }

    // Put Member Update API
    @PreAuthorize("permitAll")
    @PutMapping("update")
    public ResponseEntity<Map<String, Integer>> postUpdateMember(@RequestBody MemberConvertDTO memberConvertDTO) {
        log.info("RestController | Api Post Update Member");
        int result = memberService.updateMember(memberConvertDTO);
        return new ResponseEntity<>(Map.of("result", result), HttpStatus.OK);
    }
}
