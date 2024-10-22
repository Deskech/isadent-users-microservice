package com.isadent.users.infrastructure.services;

import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositoryUserAccess;
import com.isadent.users.domain.services.UserAuthenticationService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * This is the UserAuthentication domain service implementation
 */
@Component
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final JwtUtil jwtUtil;
    private final RepositoryUserAccess repositoryUserAccess;
    public UserAuthenticationServiceImpl(JwtUtil jwtUtil, RepositoryUserAccess repositoryUserAccess){
        this.jwtUtil = jwtUtil;
        this.repositoryUserAccess =repositoryUserAccess;
    }

    /**
     *
     * @param userCredentials Represents the UserCredentials request
     * @return a new UserAccess instance including the authentication token
     */
    @Override
    public Mono<UserAccess> authenticateAndGenerateToken(UserCredentials userCredentials) {
        return repositoryUserAccess.validateUserCredentials(userCredentials).map(validatedCredentials->{
            String token = jwtUtil.generateToken(userCredentials);
            return  new UserAccess(token,validatedCredentials);
        });

    }
}
