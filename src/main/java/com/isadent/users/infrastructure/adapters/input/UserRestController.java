package com.isadent.users.infrastructure.adapters.input;


import com.isadent.users.application.services.AuthenticateUser;
import com.isadent.users.application.services.RegisterUser;
import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * This is the authorization rest controller
 */
@RestController
@CrossOrigin
public class UserRestController {
    private final AuthenticateUser authenticateUser;
    private final RegisterUser registerUser;

    public UserRestController(AuthenticateUser authenticateUser, RegisterUser registerUser) {
        this.authenticateUser = authenticateUser;
        this.registerUser = registerUser;
    }


    @PostMapping("/users/login")
    public Mono<UserAccess> userAccessMono(@RequestBody UserCredentials userCredentials) {
        return authenticateUser.validateUser(userCredentials);
    }


    @PostMapping("/users/register")
    public Mono<Void> save(@RequestBody UserCredentials userCredentials) {

        return registerUser.saveUser(userCredentials);
    }

}
