package com.example.lab05.Service_REST.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CarNotFoundEx extends RuntimeException{
    public CarNotFoundEx(int i){
        super("Car " + i + " not found");
    }
}
