package de.gugaglonti.jollyapi.modules.user;

import de.gugaglonti.jollyapi.modules.auth.dto.request.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import de.gugaglonti.jollyapi.modules.user.entities.User;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;


  public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User findByIdentifier(String identifier) {
    if (identifier.contains("@")) {
      return userRepository.findByEmail(identifier);
    } else {
      return userRepository.findByUsername(identifier);
    }
  }

  public Boolean usernameExists(String email) {
    return userRepository.existsByUsername(email);
  }

  public User createNewUser(SignUpDto dto) {
    return userRepository.save(
        User.builder()
            .firstName(dto.firstName())
            .lastName(dto.lastName())
            .email(dto.email())
            .username(dto.username())
            .password(dto.password())
            .build()
    );
  }
}
