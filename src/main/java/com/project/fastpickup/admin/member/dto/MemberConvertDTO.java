package com.project.fastpickup.admin.member.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberConvertDTO {
    // tbl_member
    @NotBlank
    private String email;
    @NotBlank
    private String memberPw;
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberPhone;
    @NotBlank
    private String store;
    @NotNull
    private LocalDateTime joinDate;
    private Long sno;
}
