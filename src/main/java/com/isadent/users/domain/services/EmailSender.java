package com.isadent.users.domain.services;

import com.isadent.users.domain.model.UserCredentials;

public interface EmailSender {

    void verificationEmail(UserCredentials userCredentials);
}
