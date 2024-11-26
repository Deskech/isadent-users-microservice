package com.isadent.users.domain.repository;

import reactor.core.publisher.Mono;

public interface RepositoryStatus {

    Mono<Boolean> isUserVerified(String email);

    Mono<Void> setEmailAsVerified(String email);
}
