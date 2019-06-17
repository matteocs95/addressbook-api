package com.matteo.app.controllers;

import com.matteo.app.repositories.UserRepository;
import com.matteo.app.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserAuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public com.matteo.app.domain.User profile(@AuthenticationPrincipal User user) {
        com.matteo.app.domain.User u = userRepository.findByEmail(user.getUsername()).get();
        return u;
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
