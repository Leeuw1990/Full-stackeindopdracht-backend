package com.jeffreyleeuweindopdracht.jeffreyeindopdracht.controller;

import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.BadRequestException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.ForbiddenException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.NotAuthorizedException;
import com.jeffreyleeuweindopdracht.jeffreyeindopdracht.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception(UsernameNotFoundException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotAuthorizedException.class)
    public ResponseEntity<Object> exception(NotAuthorizedException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> exception(ForbiddenException exception) {
        String message = exception.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

}
