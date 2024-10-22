package com.isadent.users.domain.repository;

import com.isadent.users.domain.model.UserCredentials;
import reactor.core.publisher.Mono;

/**
 * This repository search the UserCredentials in the redis memory
 */
public interface RepositoryUserAccess {

    Mono <UserCredentials> validateUserCredentials(UserCredentials userCredentials);
}
