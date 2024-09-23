package de.gugaglonti.jollyapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import de.gugaglonti.jollyapi.modules.user.entities.User;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

  private final JwtProperties jwtProperties;


  public String issue(
      User user
  ) {

    return JWT.create()
        .withSubject((user.getId()).toString())
        .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
        .withIssuedAt(Instant.now())
        // .withClaim("roles", roles.stream().map(Role::name).toList())
        .withIssuer("jolly-api")
        .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
  }
}
