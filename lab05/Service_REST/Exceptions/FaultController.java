package com.example.lab05.Service_REST.Exceptions;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {
    @ResponseBody
    @ExceptionHandler(CarNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ResponseEntity<Problem> PNFEHandler(CarNotFoundEx e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(org.springframework.hateoas.mediatype.problem.Problem.create()
                        .withStatus(HttpStatus.NOT_FOUND) .
                        withTitle(HttpStatus.NOT_FOUND.name())
                        .withDetail(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(CarExistsEx.class)
    @ResponseStatus(value = HttpStatus.FOUND)
    ResponseEntity<Problem> PFEHandler(CarExistsEx e) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withStatus(HttpStatus.FOUND) .
                        withTitle(HttpStatus.FOUND.name())
                        .withDetail(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(CarBadStatusEx.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<?> ConflictHandler(CarBadStatusEx e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withStatus(HttpStatus.CONFLICT) .
                        withTitle(HttpStatus.CONFLICT.name())
                        .withDetail(e.getMessage()));
    }
}
