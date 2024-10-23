package com.isadent.users.application.services;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositorySaveUserAccess;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegisterUser {
    private final RepositorySaveUserAccess saveUserAccess;

    public RegisterUser(RepositorySaveUserAccess saveUserAccess) {
        this.saveUserAccess = saveUserAccess;
    }

    public Mono<Void> saveUser(UserCredentials userCredentials) {
        return saveUserAccess.save(userCredentials);
    }
}
