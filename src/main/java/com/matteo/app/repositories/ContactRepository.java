package com.matteo.app.repositories;

import com.matteo.app.domain.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Optional<Contact> findByEmail(String email);
}
