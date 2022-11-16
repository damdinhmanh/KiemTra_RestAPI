package com.example.loginfunction.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {

        super("Doi mat khau khong thanh cong!!!");
    }
}
