package com.project.fastpickup.admin.like.dto;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    // tbl_like
    private String email;
    private Long pno;
    private LocalDate createDate;
}
