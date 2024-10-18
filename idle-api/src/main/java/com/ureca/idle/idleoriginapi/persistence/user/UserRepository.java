package com.ureca.idle.idleoriginapi.persistence.user;

import com.ureca.idle.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
