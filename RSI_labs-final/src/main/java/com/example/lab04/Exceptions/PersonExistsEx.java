package com.example.lab04.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PersonExistsEx extends RuntimeException{
    public PersonExistsEx(String message){
        super(message);
    }
}
