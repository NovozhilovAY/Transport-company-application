package com.example.transportcompanyapplication.exceptions;

public class DetailedResponseError extends ResponseError{
    private String details;

    public DetailedResponseError(String message, String details) {
        super(message);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
