package com.project.fastpickup.admin.member.mappers;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */


import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.fastpickup.admin.member.dto.MemberConvertDTO;
import com.project.fastpickup.admin.member.dto.MemberReadDTO;
import com.project.fastpickup.admin.util.PageRequestDTO;

@Mapper
public interface MemberMapper {

    // Security Member Read
    MemberReadDTO selectOne(String email);

    // Create Role
    @Insert("INSERT INTO tbl_member_role (email, rolename) VALUES (#{email}, #{rolename})")
    int createJoinMemberRole(@Param("email") String email, @Param("rolename") String rolename);

    // Create Member 
    int joinMember(MemberConvertDTO memberConvertDTO);

    // Delete Member 
    int deleteMember(String email);

    // Delete Member Role
    int deleteMemberRole(String email);

    // Update Member 
    int updateMember(MemberConvertDTO memberConvertDTO);

    // Read Member
    MemberConvertDTO readMember(String email);

    // total 
    int total(PageRequestDTO pageRequestDTO);

    // UserEmailAlreadyExistsException
    int checkEmailForException(String email);
}
