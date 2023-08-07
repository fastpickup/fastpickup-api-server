package com.project.fastpickup.admin.member.dto;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class MemberDTO extends User implements OAuth2User {

  private String email;
  private String memberName;
  private String memberPw;
  private List<String> roleNames = new ArrayList<>();

  public MemberDTO(String email, String memberPw, String memberName, List<String> roleNames) {

    // super(email,mpw, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    super(email, memberPw,
      roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
    this.memberName = memberName;
    this.email = email;
    this.memberPw = memberPw;
    this.roleNames = roleNames;
  }

  public Map<String, Object> getClaims() {

    // Mutable 해야한다.
    Map<String, Object> map = new HashMap<>();

    map.put("email", email);
    map.put("memberPw", memberPw);
    map.put("memberName", memberName);
    map.put("roleNames", roleNames);
    return map;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public String getName() {
    return this.email;
  }

  public void setProps(Map<String, Object> params) {
  }
}
