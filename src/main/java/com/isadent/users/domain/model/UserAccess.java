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
    private final UserCredentials userCredentials;

    public UserAccess(String authToken, UserCredentials userCredentials){
        this.userCredentials= userCredentials;
        this.authToken= authToken;
    }
}
