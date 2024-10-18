package com.ureca.idle.idleoriginapi.common.exception.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(BaseException e) {
        log.warn(e.getMessage(), e);
        ExceptionType type = e.getExceptionType();
        ExceptionStatus exceptionStatus = ExceptionStatus.from(type.status());
        return ResponseEntity.status(exceptionStatus.getHttpStatus())
                .body(new ExceptionResponse(type.message()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(e.getMessage()));
    }
}
