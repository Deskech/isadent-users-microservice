package com.isadent.users.infrastructure.services;

import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.services.UserAuthenticationService;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public UserAccess authenticateAndGenerateToken(UserCredentials userCredentials) {
        return null; //on development
    }
}
