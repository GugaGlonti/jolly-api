package de.gugaglonti.jollyapi.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtToPrincipalConverter {

  public UserPrincipal convert(DecodedJWT jwt) {
    return UserPrincipal.builder()
        .userId(Long.valueOf(jwt.getSubject()))
        .authorities(extractAuthoritiesFromClaim(jwt))
        // .email(jwt.getClaim("email").asString())
        .build();
  }

  private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
    var claim = jwt.getClaim("roles");
    if (claim.isNull() || claim.isMissing()) {
      return List.of();
    }
    return claim.asList(SimpleGrantedAuthority.class);
  }

}
