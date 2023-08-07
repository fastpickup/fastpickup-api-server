package com.project.fastpickup.admin.util;

/*
 * Date   : 2023.07.26
 * Author : 조상희
 * E-mail : jo_sh_1028@naver.com
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDTO {
  // 변수 선언
  @Builder.Default
  private int page = 1; // page번호
  @Builder.Default
  private int size = 10; // size
  private String type; // 검색type
  private String keyword; // 검색어
  private String link; // 검색조건, 페이지, 사이즈 통합
  private boolean replyLast; // 댓글 페이징 마지막 페이지 유무


  private LocalDate startDate;
  private LocalDate endDate;

  // page번호 음수값 제외처리
  public void setPage(int page) {
    if (page < 0) {
      this.page = 1;
    } else {
      this.page = page;
    }
  }

  // size 음수값, 과도한 값 제외처리
  public void setSize(int size) {
    if (size < 0 || size > 100) {
      this.size = 10;
    } else {
      this.size = size;
    }
  }

  // limit에 들어갈 skip 계산
  public int getSkip() {
    return (this.page - 1) * this.size;
  }

  // 다음페이지를 위한 count
  public int getCountEnd() {
    return ((int) Math.ceil(this.page / 10.0) * (10 * this.size)) + 1;
  }

  // type 배열로 반환 처리
  public String[] getTypes() {
    if (this.type == null || this.type.isEmpty()) {
      return null;
    }
    return this.type.split("");
  }

  public String getLink() {

    if (link == null) {
      // 문자열 합치기
      StringBuilder strBuilder = new StringBuilder();

      // page, size 쿼리스트링 전달
      strBuilder.append("page=" + this.page);
      strBuilder.append("&size=" + this.size);

      // 검색타입
      if (type != null && type.length() > 0) {
        strBuilder.append("&type=" + this.type);
      }

      // 검색어
      if (keyword != null && keyword.length() > 0) {
        try {
          strBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      }

      // startDate
      if (startDate != null) {
        strBuilder.append("&startDate=" + startDate.toString()); // LocalDate의 경우 ISO 8601 날짜 형식으로 변환됩니다.
      }

      // endDate
      if (endDate != null) {
        strBuilder.append("&endDate=" + endDate.toString());
      }

      // toString으로 합친 문자열 String으로 반환
      link = strBuilder.toString();
    }

    return link;
  }

}