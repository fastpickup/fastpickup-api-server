package com.project.fastpickup.admin.adviceexception.errorenum;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import org.springframework.http.HttpStatus;

import java.util.function.Function;

public enum AdviceErrorCode {

    METHOD_ARUGMNET_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, ex -> "Method Argument Type Mismatch: " + ex.getMessage()),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, ex -> "Data Not Found: " + ex.getMessage()),
    BIND_EXCEPTION(HttpStatus.BAD_REQUEST, ex -> "Bind Exception: " + ex.getMessage()),
    DATA_INTEGRITY_VIOLATION(HttpStatus.BAD_REQUEST, ex -> "Data Integrity Violation: " + ex.getMessage()),
    NO_SUCH_ELEMENT(HttpStatus.BAD_REQUEST, ex -> "No Such Element: " + ex.getMessage()),
    NULL_POINTER(HttpStatus.INTERNAL_SERVER_ERROR, ex -> "Null Pointer Exception: " + ex.getMessage()),
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST, ex -> "Illegal Argument Exception: " + ex.getMessage()),
    ARRAY_INDEX_OUT_OF_BOUNDS(HttpStatus.BAD_REQUEST, ex -> "Array Index Out Of Bounds Exception: " + ex.getMessage()),
    NUMBER_FORMAT(HttpStatus.BAD_REQUEST, ex -> "Number Format Exception: " + ex.getMessage()),
    HTTP_MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, ex -> "HTTP Message Not Readable Exception: " + ex.getMessage()),
    HTTP_CLIENT_ERROR(HttpStatus.BAD_REQUEST, ex -> "HTTP Client Error Exception: " + ex.getMessage()),
    HTTP_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ex -> "HTTP Server Error Exception: " + ex.getMessage()),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, ex -> "Resource Not Found Exception: " + ex.getMessage()),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, ex -> "Access Denied Exception: " + ex.getMessage());

    private final HttpStatus status;
    private final Function<Exception, String> messageExtractor;

    AdviceErrorCode(HttpStatus status, Function<Exception, String> messageExtractor) {
        this.status = status;
        this.messageExtractor = messageExtractor;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage(Exception ex) {
        return messageExtractor.apply(ex);
    }
}
