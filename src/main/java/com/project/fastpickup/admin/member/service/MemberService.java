package com.project.fastpickup.admin.member.service;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;

// Member Service Interface
public interface MemberService {
    
    // Create Member 
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Delete Member 
    int deleteMember(String email);

    // Update Member 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Read Member
    MemberConvertDTO readMember(String email);

    // checkEmailAlreadyExists
    void checkEmailAlreadyExists(String email);

    // searchUser
    void searchUser(String email);
}
