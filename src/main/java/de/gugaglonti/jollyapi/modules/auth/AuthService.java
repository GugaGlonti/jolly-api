package de.gugaglonti.jollyapi.modules.auth;

import de.gugaglonti.jollyapi.modules.auth.exception.UserAlreadyExistsException;
import de.gugaglonti.jollyapi.security.JwtIssuer;
import de.gugaglonti.jollyapi.modules.auth.dto.response.AuthToken;
import de.gugaglonti.jollyapi.modules.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.gugaglonti.jollyapi.modules.auth.dto.request.SignInDto;
import de.gugaglonti.jollyapi.modules.auth.dto.request.SignUpDto;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtIssuer jwtIssuer;
  private final UserService userService;

  public AuthToken signUp(
      SignUpDto dto
  ) {
    if (this.userService.usernameExists(dto.username())) {
      throw new UserAlreadyExistsException("Username already exists");
    }
    this.userService.createNewUser(dto);
    return this.signIn(new SignInDto(dto.email(), dto.password()));
  }

  public AuthToken signIn(
      SignInDto dto
  ) {
    var user = this.userService.findByIdentifier(dto.identifier());
    var token = this.jwtIssuer.issue(user.getId(), user.getRoles());
    return AuthToken.builder().accessToken(token).build();
  }
}
