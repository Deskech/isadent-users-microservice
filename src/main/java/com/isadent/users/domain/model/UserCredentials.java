package com.isadent.users.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * This class is the representation of the user's request
 */
@EqualsAndHashCode
@Getter
public class UserCredentials implements Serializable {
    private final String email;
    private final String username;
    private final String password;

    public UserCredentials(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
