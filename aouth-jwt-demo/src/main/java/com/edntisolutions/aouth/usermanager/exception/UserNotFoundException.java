package com.edntisolutions.aouth.usermanager.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user not found");
    }

}
