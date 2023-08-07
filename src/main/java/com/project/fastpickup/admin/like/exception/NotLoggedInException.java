package com.project.fastpickup.admin.like.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

 // 로그인 하지 않은 상태일때 NotLoggedInException
public class NotLoggedInException extends RuntimeException {
    
    public NotLoggedInException() {
        super();
    }

    public NotLoggedInException(String message) {
        super(message);
    }
}
