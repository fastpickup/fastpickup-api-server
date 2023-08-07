package com.project.fastpickup.admin.member.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// 유저가 없을때 UserNotFoundException
public class UserNotFoundException extends RuntimeException {
    
     public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
