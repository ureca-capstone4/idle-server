package com.ureca.idle.idleoriginapi.common.exception.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;


@AllArgsConstructor
public enum ExceptionStatus {

    INTERNAL_SERVER_ERROR(Status.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(Status.NOT_FOUND, HttpStatus.NOT_FOUND),
    UNAUTHORIZED(Status.UNAUTHORIZED, HttpStatus.UNAUTHORIZED),
    FORBIDDEN(Status.FORBIDDEN, HttpStatus.FORBIDDEN),
    BAD_REQUEST(Status.BAD_REQUEST, HttpStatus.BAD_REQUEST),
    ;

    private final Status status;
    @Getter
    private final HttpStatus httpStatus;

    public static ExceptionStatus from(Status input) {
        return Arrays.stream(ExceptionStatus.values())
                .filter(it -> it.status == input)
                .findAny()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}

