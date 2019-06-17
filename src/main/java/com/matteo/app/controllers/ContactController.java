package com.matteo.app.controllers;

import com.matteo.app.domain.Contact;
import com.matteo.app.services.ContactService;
import com.matteo.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public Set<Contact> getAllUserContact(@AuthenticationPrincipal User user){
        return contactService.getByUser(user.getUsername());
    }


    @GetMapping("/all")
    public Iterable<Contact> getAll() {
        return contactService.getList();
    }

    @GetMapping("/{id}")
    public Contact getById(@PathVariable("id") Integer id) {
        return contactService.getById(id)
                .orElse(null);
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact, @AuthenticationPrincipal User user) {
        Contact res = contactService.create(contact);
        userService.addEmployeeToUser(user.getUsername(), contact.getEmail());
        return res;
    }

    @PutMapping("/{id}")
    public Contact update(@PathVariable("id") String id, @RequestBody Contact contact) {
        return contactService.update(contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        contactService.delete(id);
    }

}
