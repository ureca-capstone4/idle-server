package com.ureca.idle.idleoriginapi.common.exception.user;

import com.ureca.idle.idleoriginapi.common.exception.config.ExceptionType;
import com.ureca.idle.idleoriginapi.common.exception.config.Status;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum UserExceptionType implements ExceptionType {

    ID_NOT_FOUND_EXCEPTION(Status.NOT_FOUND, "해당 ID 를 가진 유저를 찾을 수 없습니다."),
    EMAIL_NOT_FOUND_EXCEPTION(Status.NOT_FOUND, "해당 이메일을 가진 유저를 찾을 수 없습니다."),
    EMAIL_OR_PASSWORD_NOT_FOUND_EXCEPTION(Status.NOT_FOUND, "해당 이메일, 혹은 비밀번호를 가진 유저를 찾을 수 없습니다."),
    LOGIN_USER_NOT_FOUND_EXCEPTION(Status.NOT_FOUND, "현재 로그인한 유저를 찾을 수 없습니다, 다시 로그인해주세요."),
    EMAIL_ALREADY_EXITS_EXCEPTION(Status.BAD_REQUEST, "이미 해당 이메일을 가진 유저가 있습니다.");

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
