package de.gugaglonti.jollyapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

  private final JwtProperties jwtProperties;


  public String issue(
      Long userId,
      Set<String> roles
  ) {
    return JWT.create()
        .withSubject(userId.toString())
        .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
        .withIssuedAt(Instant.now())
        .withClaim("roles", roles.toString())
        .withIssuer("jolly-api")
        .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
  }
}
