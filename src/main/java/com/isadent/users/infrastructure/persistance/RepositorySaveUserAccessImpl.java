package com.isadent.users.infrastructure.persistance;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositorySaveUserAccess;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * this is the repositorySaveUserAccess implementation
 */
@Component
public class RepositorySaveUserAccessImpl implements RepositorySaveUserAccess {
    private final PasswordEncoder passwordEncoder;
    private final ReactiveHashOperations<String, String, String> hashOperations;
    public RepositorySaveUserAccessImpl(PasswordEncoder passwordEncoder,ReactiveHashOperations<String, String, String> hashOperations){
        this.passwordEncoder = passwordEncoder;
        this.hashOperations = hashOperations;
    }

    /**
     *
     * @param userCredentials represents the request to create a new user
     * @return saves the user in the redis db
     */
    @Override
    public Mono<Void> save(UserCredentials userCredentials) {
        String userKey = "user:" + userCredentials.getEmail();

        return hashOperations.get(userKey, "email")
                .hasElement()
                .flatMap(exists ->{
                    if (!exists) {
                        String password = passwordEncoder.encode(userCredentials.getPassword());
                        return hashOperations.put(userKey, "email",userCredentials.getEmail())
                                .then(hashOperations.put(userKey,"password",password));
                    }else{
                        return Mono.error(new Exception("User already exists"));
                    }
                })
                .then();
    }
}
