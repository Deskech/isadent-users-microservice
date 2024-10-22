package com.isadent.users.domain.repository;

import com.isadent.users.domain.model.UserCredentials;
import reactor.core.publisher.Mono;

/**
 * This interface saves the UserCredentials in the redis memory
 */
public interface RepositorySaveUserAccess {

    Mono<Void> save(UserCredentials userCredentials);
}
