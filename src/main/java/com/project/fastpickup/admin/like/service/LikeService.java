package com.project.fastpickup.admin.like.service;

import com.project.fastpickup.admin.like.dto.LikeDTO;

/*
 * Date   : 2023.07.31
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// Like Service Interface 
public interface LikeService {

    // Toggle Like Service
    int toggleLike(Long pno, String email);

    // Count Like service
    Long countLike(Long pno);

    // Check Member Like And Post
    LikeDTO checkLikeByMemberAndPost(Long pno, String email);

    // checkPnoNotFound
    void checkPnoNotFound(Long pno);

    // checkEmailNotLogin
    void checkEmailNotLogin(String email);
}
