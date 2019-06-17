package com.matteo.app.services;

import com.matteo.app.domain.Contact;
import com.matteo.app.domain.User;
import com.matteo.app.repositories.ContactRepository;
import com.matteo.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getByToken(String token) {
        return userRepository.findByToken(token);
    }

    public Optional<User> getByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public User addEmployeeToUser(String email, String contactEmail){

        Optional<Contact> contact = contactRepository.findByEmail(contactEmail);
        Optional<User> user = userRepository.findByEmail(email);
        user.get().addContact(contact.get());
        return userRepository.save(user.get());
    }

    public boolean hasContactId(String userEmail, Contact contact){
        Set<Contact> contactList = userRepository.findByEmail(userEmail).get().getContacts();
        if (contactList.contains(contact)){
            return true;
        }else{
            return false;
        }
    }

}
