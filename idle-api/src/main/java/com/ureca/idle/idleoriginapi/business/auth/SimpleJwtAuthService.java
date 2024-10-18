package com.ureca.idle.idleoriginapi.business.auth;

import com.ureca.idle.idleoriginapi.business.auth.dto.LoginReq;
import com.ureca.idle.idleoriginapi.business.auth.dto.LoginResp;
import com.ureca.idle.idleoriginapi.business.auth.dto.SignupReq;
import com.ureca.idle.idleoriginapi.business.auth.dto.SignupResp;
import com.ureca.idle.idleoriginapi.implementation.mapper.AuthDtoMapper;
import com.ureca.idle.idleoriginapi.implementation.user.UserManager;
import com.ureca.idle.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleJwtAuthService implements AuthUseCase {

    private final UserManager userManager;
    private final AuthDtoMapper authDtoMapper;

    @Override
    public LoginResp login(LoginReq req) {
        User loginUser = userManager.getUserByEmailAndPassword(req.email(), req.password());
        return authDtoMapper.toLoginResp(loginUser);
    }

    @Override
    public void logout() {

    }

    @Override
    @Transactional
    public SignupResp signup(SignupReq req) {
        userManager.checkExitsUserByEmail(req.email());
        User registeredUser = userManager.registerUser(req.email(), req.password(), req.name(), req.phoneNum());
        return authDtoMapper.toSignupResp(registeredUser);
    }

    @Override
    public void withdraw() {

    }
}
