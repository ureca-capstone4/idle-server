package com.ureca.idle.idleoriginapi.common.exception.kid;

import com.ureca.idle.idleoriginapi.common.exception.config.BaseException;
import com.ureca.idle.idleoriginapi.common.exception.config.ExceptionType;



public class KidException extends BaseException {

    public KidException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}

