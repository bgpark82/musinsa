package com.bgpark.musinsa.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode code;

    private String exception;

    public BusinessException(String message, ErrorCode code, String exception) {
        super(message);
        this.code = code;
        this.exception = exception;
    }

    public BusinessException(ErrorCode code, Throwable exception) {
        super(exception.getMessage());
        this.code = code;
        this.exception = exception.getClass().getName();;
    }

    public BusinessException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
