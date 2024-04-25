package com.example.lab04.Exceptions;

import com.example.lab04.Repo.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class StatusEx extends RuntimeException{
    public StatusEx(Person.PersonStatus status){
        super("Can't change " + status.name() + " status.");
    }
}
