package com.example.lab05.Service_REST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CarExistsEx extends RuntimeException{
    public CarExistsEx(String message){
        super(message);
    }
}
