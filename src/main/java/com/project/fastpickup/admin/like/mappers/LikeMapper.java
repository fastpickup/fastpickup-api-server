package com.project.fastpickup.admin.like.mappers;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.like.dto.LikeCreateDTO;
import com.project.fastpickup.admin.like.dto.LikeDTO;

// Like Mapper Interface
public interface LikeMapper {
    
    // Create Like
    int createLike(LikeCreateDTO likeCreateDTO);

    // Delete Like
    int deleteLike(LikeDTO likeDTO);

    // Count Like
    Long countLikes(Long pno);

    // Check Like For Member 
    LikeDTO checkLikeByMemberAndPost(@Param("pno") Long pno, @Param("email") String email);

    // IncreaMent Like For tbl_product
    int incrementLikeCount(Long pno);

    // Decrement Like For tbl_product
    int decrementLikeCount(Long pno);

    // Check pno
    int duplicatePno(Long pno);

    // Check Email 
    int duplicateEmail(String email);
}
