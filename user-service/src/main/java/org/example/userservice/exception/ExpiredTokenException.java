package org.example.userservice.exception;

public class ExpiredTokenException extends RuntimeException{
    public ExpiredTokenException(String message) {
        super(message);
    }
}
