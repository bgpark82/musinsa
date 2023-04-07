package com.bgpark.musinsa.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 상태 코드와 상태 코드를 구분할 커스텀 코드를 가진 클래스
 * @author 박병길
 */
@Getter
public enum ErrorCode {

    INVALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, 4001),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, 5000),
    INVALID_ENCRYPTION(HttpStatus.INTERNAL_SERVER_ERROR, 5001),
    NULL(HttpStatus.NOT_FOUND, 4002);

    /**
     * 에러 상태 코드
     */
    private HttpStatus status;

    /**
     * 에러 상태 코드를 구분할 커스텀 코드
     */
    private int code;

    ErrorCode(HttpStatus status, int code) {
        this.status = status;
        this.code = code;
    }
}
