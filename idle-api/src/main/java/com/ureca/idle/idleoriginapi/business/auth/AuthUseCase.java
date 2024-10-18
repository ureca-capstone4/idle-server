package com.ureca.idle.idleoriginapi.business.auth;

import com.ureca.idle.idleoriginapi.business.auth.dto.LoginReq;
import com.ureca.idle.idleoriginapi.business.auth.dto.LoginResp;
import com.ureca.idle.idleoriginapi.business.auth.dto.SignupReq;
import com.ureca.idle.idleoriginapi.business.auth.dto.SignupResp;

public interface AuthUseCase {
    LoginResp login(LoginReq req);
    void logout();
    SignupResp signup(SignupReq req);
    void withdraw();
}