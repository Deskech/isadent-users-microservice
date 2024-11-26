package com.isadent.users.application.services;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositorySaveUserAccess;
import com.isadent.users.domain.services.EmailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegisterUser {
    private final RepositorySaveUserAccess saveUserAccess;
    private final EmailSender emailSender;

    public RegisterUser(RepositorySaveUserAccess saveUserAccess, EmailSender emailSender) {
        this.saveUserAccess = saveUserAccess;
        this.emailSender = emailSender;
    }

    public Mono<Void> saveUser(UserCredentials userCredentials) {
        return saveUserAccess.save(userCredentials).then(
                Mono.fromRunnable(() -> emailSender.verificationEmail(userCredentials))
        );
    }
}
