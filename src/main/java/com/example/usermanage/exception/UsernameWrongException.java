package com.example.loginfunction.exception;

public class UsernameWrongException extends RuntimeException {
    public UsernameWrongException(String username) {
        super("Username= "  + username + " -> is not correct!");
    }
}
