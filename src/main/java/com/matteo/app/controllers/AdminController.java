package com.matteo.app.controllers;

import com.matteo.app.domain.Contact;
import com.matteo.app.domain.User;
import com.matteo.app.repositories.ContactRepository;
import com.matteo.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/users")
    @ResponseBody
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/contacts")
    @ResponseBody
    public Iterable<Contact> getAllContacts(){
        return contactRepository.findAll();
    }



}
