package com.isadent.users.infrastructure.services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.isadent.users.domain.model.UserCredentials;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    Dotenv dotenv = Dotenv.load();
    private final String SECRET_KEY = dotenv.get("SECRET_KEY");
    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(UserCredentials userCredentials){
        return JWT.create()
                .withClaim("email", userCredentials.getEmail())
                .withClaim("username", userCredentials.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+3600000))
                .sign(algorithm);
    }
}
