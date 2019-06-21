package com.matteo.app.controllers;

import com.matteo.app.services.UserAuthenticationService;
import com.matteo.app.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
public class PublicEndpointsController {
    @Autowired
    private UserRegistrationService registrationService;
    @Autowired
    private UserAuthenticationService authenticationService;

    @PostMapping("/register")
    public Object register(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        try {
            return registrationService
                    .register(email, password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public Object login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

            return authenticationService
                    .login(email, password);
    }
}