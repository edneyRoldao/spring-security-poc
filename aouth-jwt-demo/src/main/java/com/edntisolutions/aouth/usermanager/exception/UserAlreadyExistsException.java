package com.edntisolutions.aouth.usermanager.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("user already exists");
    }

}
