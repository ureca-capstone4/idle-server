package com.ureca.idle.idleoriginapi.common.exception.kid;


import com.ureca.idle.idleoriginapi.common.exception.config.ExceptionType;
import com.ureca.idle.idleoriginapi.common.exception.config.Status;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum KidExceptionType implements ExceptionType {

    NOT_FOUND_EXCEPTION(Status.NOT_FOUND, "해당 KID 를 찾을 수 없습니다."),
    ALREADY_EXITS_NAME(Status.BAD_REQUEST, "이미 존재하는 이름의 KID 입니다. "),
    HI_THERE(Status.SERVER_ERROR, "안되네?");

    private final Status status;
    private final String message;

    @Override
    public Status status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }
}
