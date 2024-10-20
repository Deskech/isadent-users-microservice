package com.isadent.users.domain.services;

import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;

public interface UserAuthenticationService {

    UserAccess authenticateAndGenerateToken(UserCredentials userCredentials);
}
