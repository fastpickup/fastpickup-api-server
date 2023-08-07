package com.project.fastpickup.admin.member.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// 이메일이 올바른 형식이 아닐시 
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super();
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
