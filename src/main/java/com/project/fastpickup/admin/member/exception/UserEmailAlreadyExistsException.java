package com.project.fastpickup.admin.member.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// 사용하는 이메일이 이미 있을경우 UserEmailAlreadyExistsException
public class UserEmailAlreadyExistsException extends RuntimeException {

     public UserEmailAlreadyExistsException() {
        super();
    }

    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}