package com.isadent.users.infrastructure.adapters.input;


import com.isadent.users.application.services.AuthenticateUser;
import com.isadent.users.application.services.RegisterUser;
import com.isadent.users.application.services.VerifyEmail;
import com.isadent.users.domain.model.UserAccess;
import com.isadent.users.domain.model.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * This is the authorization rest controller
 */
@RestController
@CrossOrigin
public class UserRestController {
    private final AuthenticateUser authenticateUser;
    private final RegisterUser registerUser;
    private final VerifyEmail verifyEmail;

    public UserRestController(AuthenticateUser authenticateUser, RegisterUser registerUser, VerifyEmail verifyEmail) {
        this.authenticateUser = authenticateUser;
        this.registerUser = registerUser;
        this.verifyEmail = verifyEmail;
    }


    @PostMapping("/users/login")
    public Mono<UserAccess> userAccessMono(@RequestBody UserCredentials userCredentials) {
        return authenticateUser.validateUser(userCredentials);
    }


    @PostMapping("/users/register")
    public Mono<Void> save(@RequestBody UserCredentials userCredentials) {

        return registerUser.saveUser(userCredentials);
    }

    @GetMapping("/verify")
    public Mono<ResponseEntity<String>> tokenVerifier(@RequestParam("token") String token) {

        return verifyEmail.fromToken(token)
                .then(Mono.just(ResponseEntity.ok("Token verified successfully")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Not possible to verify token")));


    }

}
