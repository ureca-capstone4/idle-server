package com.ureca.idle.idleoriginapi.implementation.kid;

import com.ureca.idle.idleoriginapi.common.exception.kid.KidException;
import com.ureca.idle.idleoriginapi.common.exception.kid.KidExceptionType;
import com.ureca.idle.idleoriginapi.persistence.kid.KidRepository;
import com.ureca.idle.kid.Gender;
import com.ureca.idle.kid.Kid;
import com.ureca.idle.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;



@Component
@RequiredArgsConstructor
public class KidManager {

    private final KidRepository repository;

    public Kid registerKid(User user, String name, String gender, LocalDate birthDate) {
        Kid newKid = Kid.builder()
                .user(user)
                .name(name)
                .gender(Gender.from(gender))
                .birthDate(birthDate)
                .build();
        return repository.save(newKid);
    }

    public List<Kid> getKidsByUser(User user) {
        return repository.getKidsByUser(user);
    }

    public Kid getKidWithPersonality(Long id) {
        return repository.findKidWithPersonalityById(id)
                .orElseThrow(() -> new KidException(KidExceptionType.NOT_FOUND_EXCEPTION));
    }

    public void checkDuplicatedKidName(User user, String name) {
        if(repository.existsByUserAndName(user, name)) {
            throw new KidException(KidExceptionType.ALREADY_EXITS_NAME);
        }
    }
}
