package com.isadent.users.domain.services;

import reactor.core.publisher.Mono;

public interface UserEmailVerification {

    Mono<Void> validateVerificationToken(String token);
}
