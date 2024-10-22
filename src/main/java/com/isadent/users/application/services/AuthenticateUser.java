package com.isadent.users.application.services;

import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.services.UserAuthenticationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * This service is used when a new login request is received
 */
@Service
public class AuthenticateUser {

    private final UserAuthenticationService authenticationService;

    public AuthenticateUser(UserAuthenticationService userAuthenticationService){
        this.authenticationService= userAuthenticationService;
    }

    /**
     *
     * @param userCredentials represents a new login request
     * @return a UserAccess object including the token for authentication
     */
    public Mono<UserAccess> validateUser(UserCredentials userCredentials){
        return authenticationService.authenticateAndGenerateToken(userCredentials);
    }
}