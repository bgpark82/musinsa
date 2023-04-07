package com.bgpark.musinsa.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 박병길
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse response = new ErrorResponse(ex);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception ex) {
        ErrorResponse response = new ErrorResponse(ErrorCode.UNKNOWN, ex.getClass().getName(), ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException ex) {
        ErrorResponse response = new ErrorResponse(ex);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
