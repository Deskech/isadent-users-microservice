package com.isadent.users.infrastructure.persistance;


import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.model.ValidatedUser;
import com.isadent.users.domain.repository.RepositoryUserAccess;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * This is the RepositoryUserAccess implementation
 */
@Component
public class RepositoryUserAccessImpl implements RepositoryUserAccess {
    private final ReactiveHashOperations<String, String, String> hashOperations;
    private final PasswordEncoder passwordEncoder;

    public RepositoryUserAccessImpl(ReactiveHashOperations<String, String, String> hashOperations,
                                    PasswordEncoder passwordEncoder

    ) {

        this.hashOperations = hashOperations;
        this.passwordEncoder = passwordEncoder;

    }

    /**
     * @param userCredentials represents the userCredentials
     * @return a UserCredentials object for token creation
     */
    @Override
    public Mono<ValidatedUser> validateUserCredentials(UserCredentials userCredentials) {

        String userKey = "user:" + userCredentials.getEmail();
        Mono<String> password = hashOperations.get(userKey, "password");
        return password.switchIfEmpty(Mono.error(new BadCredentialsException("User not found")))
                .flatMap(stored -> {
                    if (passwordEncoder.matches(userCredentials.getPassword(), stored)) {
                        return Mono.just(new ValidatedUser(userCredentials.getEmail(),
                                userCredentials.getUsername()
                        ));
                    } else {
                        return Mono.error(new BadCredentialsException("Invalid email or password"));
                    }
                });

    }
}
