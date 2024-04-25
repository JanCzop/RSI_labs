package com.example.lab04.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundEx extends RuntimeException{
    public PersonNotFoundEx(int i){
        super("Person " + i + " not found");
    }
}
