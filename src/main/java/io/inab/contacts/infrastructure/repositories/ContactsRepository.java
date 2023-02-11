package io.inab.contacts.infrastructure.repositories;

import io.inab.contacts.domain.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByLastName(String lastName);
    List<Contact> findByEmail(String email);
    List<Contact> findByCompany(String company);
    List<Contact> findByNickname(String nickname);
}
