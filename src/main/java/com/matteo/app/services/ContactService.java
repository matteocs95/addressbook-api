package com.matteo.app.services;

import com.matteo.app.domain.Contact;
import com.matteo.app.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    public Iterable<Contact> getList() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getById(int id) {
        return contactRepository.findById(id);
    }

    public Contact create(Contact contact) {
        contactRepository.findByEmail(contact.getEmail())
                .ifPresent(c -> {
                    throw  new IllegalArgumentException("Contact with this email already added");
                });
        return contactRepository.save(contact);
    }

    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

    public Optional<Contact> getByEmail(String email) { return contactRepository.findByEmail(email);}

    public Set<Contact> getByUser(String userEmail){

        Set<Contact> contactSet = new HashSet<>();
        for (Contact contact : contactRepository.findAll()){
            if (userService.hasContactId(userEmail, contact)){
                contactSet.add(contact);
            }
        }
        return contactSet;
    }

}
