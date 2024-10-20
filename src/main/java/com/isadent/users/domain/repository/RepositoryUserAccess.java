package com.isadent.users.domain.repository;

import com.isadent.users.domain.model.UserCredentials;

public interface RepositoryUserAccess {

    UserCredentials validateUserCredentials(UserCredentials userCredentials);
}
