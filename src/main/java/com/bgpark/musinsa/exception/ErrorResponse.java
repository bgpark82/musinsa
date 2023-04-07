package com.bgpark.musinsa.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

/**
 * 에러 발생 시, ResponseEntity 바디에 담긴 에러 상세 내용
 * @author 박병길
 */
@Getter
@NoArgsConstructor
public class ErrorResponse {

    private ErrorCode errorCode;

    private String exception;

    private String invalidField;

    private String invalidValue;

    private String message;

    public ErrorResponse(MethodArgumentNotValidException ex) {
        final FieldError fieldError = ex.getBindingResult().getFieldError();

        this.errorCode = ErrorCode.INVALID_METHOD_ARGUMENT;
        this.exception = ex.getClass().getSimpleName();
        this.invalidField = Optional.ofNullable(fieldError.getField()).orElse(null);
        this.invalidValue = Optional.ofNullable(fieldError.getRejectedValue()).map(v -> v.toString()).orElse(null);
        this.message = fieldError.getDefaultMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String exception, String message) {
        this.errorCode = errorCode;
        this.exception = exception;
        this.message = message;
    }

    public ErrorResponse(BusinessException ex) {
        this.errorCode = ex.getCode();
        this.exception = ex.getException();
        this.message = ex.getMessage();
    }

    public HttpStatus getStatus() {
        return this.errorCode.getStatus();
    }

    public int getCode() {
        return this.errorCode.getCode();
    }
}
