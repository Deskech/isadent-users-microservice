package com.isadent.users.domain.services;

import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import reactor.core.publisher.Mono;

public interface UserLoginService {

    Mono<UserAccess> authenticateAndGenerateToken(UserCredentials userCredentials);
}
