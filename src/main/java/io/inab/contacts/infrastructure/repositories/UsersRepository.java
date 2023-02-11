package io.inab.contacts.infrastructure.repositories;

import io.inab.contacts.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
