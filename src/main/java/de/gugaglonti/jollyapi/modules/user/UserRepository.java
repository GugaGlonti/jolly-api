package de.gugaglonti.jollyapi.modules.user;

import de.gugaglonti.jollyapi.modules.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

  User findByEmail(String identifier);

  Boolean existsByUsername(String email);
}
