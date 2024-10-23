package com.isadent.users.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class represents the response given to the user to have access
 */
@EqualsAndHashCode
@Getter
public class UserAccess {
    private final String authToken;
    private final ValidatedUser validatedUser;

    public UserAccess(String authToken, ValidatedUser validatedUser) {
        this.validatedUser = validatedUser;
        this.authToken = authToken;
    }
}
