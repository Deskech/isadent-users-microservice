package com.isadent.users.infrastructure.persistance;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositoryUserAccess;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUserAccessImpl implements RepositoryUserAccess {

    @Override
    public UserCredentials validateUserCredentials(UserCredentials userCredentials) {
        return null; // on development
    }
}
