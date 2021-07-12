package com.edntisolutions.aouth.config;

import com.edntisolutions.aouth.security.exception.AppUnauthorizedException;
import com.edntisolutions.aouth.security.exception.TokenFormatException;
import com.edntisolutions.aouth.usermanager.exception.UserAlreadyExistsException;
import com.edntisolutions.aouth.usermanager.exception.UserNotFoundException;
import com.edntisolutions.aouth.usermanager.exception.UserPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<String> handleUserExists(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserPasswordException.class)
    public final ResponseEntity<String> handleUserPassword(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<String> handleUserNotFound(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(TokenFormatException.class)
    public final ResponseEntity<String> handleTokenFormatException(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(AppUnauthorizedException.class)
    public final ResponseEntity<String> handleAppUnauthorizedException(Exception e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
