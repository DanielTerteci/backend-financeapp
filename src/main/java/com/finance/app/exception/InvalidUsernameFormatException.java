package com.finance.app.exception;

public class InvalidUsernameFormatException extends RuntimeException{
    public InvalidUsernameFormatException(String message){
        super(message);
    }
}
