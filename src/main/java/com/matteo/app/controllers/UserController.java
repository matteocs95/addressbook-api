package com.matteo.app.controllers;

import com.matteo.app.repositories.UserRepository;
import com.matteo.app.services.UserAuthenticationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserAuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public com.matteo.app.domain.User profile(@AuthenticationPrincipal User user) {
        try {
            com.matteo.app.domain.User u = userRepository.findByEmail(user.getUsername()).get();
            return u;
        }catch (UsernameNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found, try again or register a new account", e
            );
        }
    }

    @PostMapping("/profile")
    public com.matteo.app.domain.User updateUserInfo(@RequestParam("name") String name, @RequestParam("lastname") String lastName,
                                                     @RequestParam("company") String company, @AuthenticationPrincipal User user){
        com.matteo.app.domain.User u = userRepository.findByEmail(user.getUsername()).get();
        u.setCompany(company);
        u.setFirstName(name);
        u.setLastName(lastName);
        return userRepository.save(u);

    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal User user) {
        authenticationService.logout(user.getUsername());
    }
}
