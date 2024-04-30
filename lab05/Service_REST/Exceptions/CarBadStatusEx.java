package com.example.lab05.Service_REST.Exceptions;

import com.example.lab05.Service_REST.Model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CarBadStatusEx extends RuntimeException{
    public CarBadStatusEx(Car.Car_status status){
        super("Can't change " + status.name() + " status.");
    }
}
