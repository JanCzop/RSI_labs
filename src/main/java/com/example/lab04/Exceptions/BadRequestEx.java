package com.example.lab04.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestEx extends RuntimeException{
    public BadRequestEx(String message){
        super(message);
    }
}
