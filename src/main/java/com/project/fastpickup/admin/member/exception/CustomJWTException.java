package com.project.fastpickup.admin.member.exception;

/*
 * Date   : 2023.08.03
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

public class CustomJWTException extends RuntimeException{

  public CustomJWTException(String msg){
      super(msg);
  }
}