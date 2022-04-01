package com.example.transportcompanyapplication.exceptions;

public class ForeignKeyException extends Exception{
    private String message;
    private String details;

    public ForeignKeyException(String message, String details) {
        super(message);
        this.message = message;
        this.details = details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
