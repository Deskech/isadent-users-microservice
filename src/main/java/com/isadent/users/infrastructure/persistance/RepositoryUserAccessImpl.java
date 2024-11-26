package com.isadent.users.infrastructure.persistance;


import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.model.ValidatedUser;
import com.isadent.users.domain.repository.RepositoryStatus;
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
    private final RepositoryStatus status;

    public RepositoryUserAccessImpl(ReactiveHashOperations<String, String, String> hashOperations,
                                    PasswordEncoder passwordEncoder, RepositoryStatus status

    ) {

        this.hashOperations = hashOperations;
        this.passwordEncoder = passwordEncoder;
        this.status = status;
    }

    /**
     * @param userCredentials represents the userCredentials
     * @return a UserCredentials object for token creation
     */
    @Override
    public Mono<ValidatedUser> validateUserCredentials(UserCredentials userCredentials) {
        String userKey = "user:" + userCredentials.getEmail();


        return status.isUserVerified(userCredentials.getEmail())
                .flatMap(isVerified ->
                {
                    if (!isVerified) {
                       return Mono.error(new BadCredentialsException("Activate your email first"));
                    }

                     Mono<String> password = hashOperations.get(userKey, "password");
                    return password.switchIfEmpty(Mono.error(new BadCredentialsException("User not found")))
                            .flatMap(storedPassword->{
                                if (passwordEncoder.matches(userCredentials.getPassword(),storedPassword)){
                                    return Mono.just(new ValidatedUser(userCredentials.getEmail(),userCredentials.getUsername()));
                                }else{
                                    return Mono.error(new BadCredentialsException("Invalid Email or Password"));
                                }
                            });
                });


    }
}
