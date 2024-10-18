package com.ureca.idle.idleoriginapi.persistence.kid;

import com.ureca.idle.kid.Kid;
import com.ureca.idle.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KidRepository extends JpaRepository<Kid, Long> {
    boolean existsByUserAndName(User user, String name);
    List<Kid> getKidsByUser(User user);
    @Query("SELECT k FROM Kid k JOIN FETCH k.personality WHERE k.id = :id")
    Optional<Kid> findKidWithPersonalityById(@Param("id") Long id);
}
