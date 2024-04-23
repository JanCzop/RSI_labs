package com.example.lab04;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class FaultController {

    @ResponseBody
    @ExceptionHandler(PersonNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    String handlePersonNotFound(PersonNotFoundEx e) {
        return HttpStatus.NOT_FOUND + " - The person with ID " + e.getMessage() + " does not exist";
    }

    @ResponseBody
    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    String handleBadRequest(BadRequestEx e) {
        return HttpStatus.BAD_REQUEST + " - " + e.getMessage();
    }
}

