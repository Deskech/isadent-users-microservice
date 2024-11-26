package com.isadent.users.application.services;

import com.isadent.users.domain.services.UserEmailVerification;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerifyEmail {
    private final UserEmailVerification userEmailVerification;

    public VerifyEmail(UserEmailVerification userEmailVerification) {
        this.userEmailVerification = userEmailVerification;
    }

    public Mono<Void> fromToken(String token) {

        return userEmailVerification.validateVerificationToken(token);
    }
}
