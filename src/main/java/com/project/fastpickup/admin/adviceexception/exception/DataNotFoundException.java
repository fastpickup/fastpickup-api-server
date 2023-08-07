package com.project.fastpickup.admin.adviceexception.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
