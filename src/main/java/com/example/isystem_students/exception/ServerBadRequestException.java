package com.example.isystem_students.exception;

public class ServerBadRequestException extends RuntimeException{
    public ServerBadRequestException(String message) {
        super(message);
    }
}
