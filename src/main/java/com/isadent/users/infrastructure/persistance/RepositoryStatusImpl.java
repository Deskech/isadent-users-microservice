package com.isadent.users.infrastructure.persistance;

import com.isadent.users.domain.repository.RepositoryStatus;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RepositoryStatusImpl implements RepositoryStatus {
    private final ReactiveHashOperations<String, String, String> hashOperations;

    public RepositoryStatusImpl(ReactiveHashOperations<String, String, String> hashOperations) {
        this.hashOperations = hashOperations;
    }

    @Override
    public Mono<Boolean> isUserVerified(String email) {

        String userKey = "user:" + email;

        return hashOperations.get(userKey, "isVerified")
                .map("true"::equalsIgnoreCase);
    }

    @Override
    public Mono<Void> setEmailAsVerified(String email) {

        String userKey = "user:" + email;

        return hashOperations.put(userKey, "isVerified", "true")
                .then();
    }
}
