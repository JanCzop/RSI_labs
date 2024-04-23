package com.example.lab04.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {

    @ResponseBody
    @ExceptionHandler(PersonNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    String handlePersonNotFound(PersonNotFoundEx e) {
        return HttpStatus.NOT_FOUND + " - " + e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    String handleBadRequest(BadRequestEx e) {
        return HttpStatus.BAD_REQUEST + " - " + e.getMessage();
    }
}
