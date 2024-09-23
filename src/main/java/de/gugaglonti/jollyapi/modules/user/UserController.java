package de.gugaglonti.jollyapi.modules.user;

import de.gugaglonti.jollyapi.modules.user.entities.User;
import de.gugaglonti.jollyapi.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/whoami")
  public User whoami(
      @AuthenticationPrincipal UserPrincipal userPrincipal
  ) {
    return userService.getUser(userPrincipal.getUserId());
  }

}
