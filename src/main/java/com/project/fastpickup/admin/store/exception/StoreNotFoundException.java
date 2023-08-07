package com.project.fastpickup.admin.store.exception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

// 가맹점을 찾을 수 없을때 StoreNotFoundException
public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException() {
        super();
    }

    public StoreNotFoundException(String message) {
        super(message);
    }
}
