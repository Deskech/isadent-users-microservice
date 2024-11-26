package com.isadent.users;

import com.isadent.users.domain.model.UserCredentials;
import com.isadent.users.domain.repository.RepositorySaveUserAccess;
import com.isadent.users.domain.services.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class UsersApplicationTests {
    @Autowired
    private RepositorySaveUserAccess saveUserAccess;
    @Autowired
    private UserLoginService authenticationService;

    @Test
    void contextLoads() {
    }

    @Rollback
    @Test
    void saveUser() {
        UserCredentials userCredentials = new UserCredentials("test@test", "test",
                "test");
        saveUserAccess.save(userCredentials)
                .doOnSuccess(success -> System.out.println("user saved successfully"))
                .doOnError(e -> System.out.println("Error:" + e.getMessage()))
                .block();


    }

    @Test
    void login() {
        UserCredentials userCredentials = new UserCredentials("test@test", "test",
                "test");

        authenticationService.authenticateAndGenerateToken(userCredentials)
                .doOnSuccess(success -> System.out.println(success.getAuthToken()))
                .doOnError(e -> System.out.println("Error" + e.getMessage()))
                .block();
    }

}
