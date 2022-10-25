package com.example.transportcompanyapplication.correcting.exceptions;

public class WrongCorrectingDataException extends RuntimeException{
    public WrongCorrectingDataException(String message) {
        super(message);
    }
}
