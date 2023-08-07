package com.project.fastpickup.admin.like.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// 상품의 번호를 찾을수 없을때 PostNotFoundException
public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
