package de.gugaglonti.jollyapi.modules.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.gugaglonti.jollyapi.modules.auth.dto.response.AuthToken;
import de.gugaglonti.jollyapi.modules.auth.dto.request.SignInDto;
import de.gugaglonti.jollyapi.modules.auth.dto.request.SignUpDto;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;


  @PostMapping("/sign-up")
  public AuthToken signUp(
      @Valid SignUpDto dto
  ) {
    return this.authService.signUp(dto);
  }

  @PostMapping("/sign-in")
  public AuthToken signIn(
      @Valid SignInDto dto
  ) {
    return this.authService.signIn(dto);
  }

}
