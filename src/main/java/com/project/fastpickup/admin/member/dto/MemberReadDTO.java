package com.project.fastpickup.admin.member.dto;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberReadDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String memberPw;
    @NotBlank
    private String memberName;
    @NotBlank
    private List<String> rolenames;
}
