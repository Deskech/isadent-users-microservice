package com.isadent.users.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class ValidatedUser {
    private final String email;
    private final String username;

    public ValidatedUser(String email, String username){
        this.email= email;
        this.username= username;
    }
}
