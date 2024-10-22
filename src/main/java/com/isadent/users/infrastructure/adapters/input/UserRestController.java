package com.isadent.users.infrastructure.adapters.input;


import com.isadent.users.application.services.AuthenticateUser;
import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * This is the authorization rest controller
 */
@RestController @CrossOrigin
public class UserRestController {
    private final AuthenticateUser authenticateUser;

    public UserRestController(AuthenticateUser authenticateUser){
        this.authenticateUser = authenticateUser;
    }


    @PostMapping("/users/login")
    public Mono<UserAccess> userAccessMono(UserCredentials userCredentials){
        return authenticateUser.validateUser(userCredentials);
    }


}
