package com.ureca.idle.idleoriginapi.common.exception.config;


import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ExceptionType exceptionType;

    public BaseException(ExceptionType exceptionType) {
        super(exceptionType.message());
        this.exceptionType = exceptionType;
    }
}

