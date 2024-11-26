package com.isadent.users.infrastructure.services;

import com.isadent.users.domain.repository.RepositoryStatus;
import com.isadent.users.domain.services.UserEmailVerification;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserEmailVerificationImpl implements UserEmailVerification {
    private final RepositoryStatus repositoryStatus;
    private final JwtUtil jwtUtil;

    public UserEmailVerificationImpl(RepositoryStatus repositoryStatus, JwtUtil jwtUtil) {
        this.repositoryStatus = repositoryStatus;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> validateVerificationToken(String token) {

        try {
            String verifiedEmail = jwtUtil.verifyToken(token);
            return repositoryStatus.setEmailAsVerified(verifiedEmail);
        } catch (Exception e) {
            throw new RuntimeException("Could not verify email", e);
        }

    }
}
