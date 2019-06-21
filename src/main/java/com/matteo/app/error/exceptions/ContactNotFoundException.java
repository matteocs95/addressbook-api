package com.matteo.app.error.exceptions;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Integer id){
        super("Contact with id " + id + " not found");
    }


}
