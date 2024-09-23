package de.gugaglonti.jollyapi.security;

import javax.security.auth.Subject;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

  private final UserPrincipal userPrincipal;

  public UserPrincipalAuthenticationToken(
      UserPrincipal userPrincipal
  ) {
    super(userPrincipal.getAuthorities());
    this.userPrincipal = userPrincipal;
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public UserPrincipal getPrincipal() {
    return this.userPrincipal;
  }

  @Override
  public boolean implies(Subject subject) {
    return super.implies(subject);
  }
}
