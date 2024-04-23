package com.example.lab04;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class PersonNotFoundEx extends Exception {
    public PersonNotFoundEx() {
        super("This person already exists");
    }
    public PersonNotFoundEx(int id) {
        super(String.valueOf(id));
    }
}