package de.gugaglonti.jollyapi.modules.auth;

import de.gugaglonti.jollyapi.modules.auth.exception.UserAlreadyExistsException;
import de.gugaglonti.jollyapi.modules.auth.exception.UserDoesNotExistException;
import de.gugaglonti.jollyapi.modules.auth.exception.WrongPasswordException;
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
  ) throws UserAlreadyExistsException {
    if (this.userService.usernameExists(dto.username())) {
      throw new UserAlreadyExistsException("Username already exists");
    }
    var user = this.userService.createNewUser(dto);
    return new AuthToken(this.jwtIssuer.issue(user));
  }

  public AuthToken signIn(
      SignInDto dto
  ) throws WrongPasswordException {
    var user = this.userService.findByIdentifier(dto.identifier());
    if (user == null) {
      throw new UserDoesNotExistException("Invalid credentials");
    }
    if (!user.checkPassword(dto.password())) {
      throw new WrongPasswordException("Invalid credentials");
    }
    return new AuthToken(this.jwtIssuer.issue(user));
  }
}
