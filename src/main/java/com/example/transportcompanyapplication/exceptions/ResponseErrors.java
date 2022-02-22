package com.example.transportcompanyapplication.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ResponseErrors {
    private List<ResponseError> errors;

    public ResponseErrors(List<ResponseError> errors) {
        this.errors = errors;
    }

    public ResponseErrors() {
        errors = new ArrayList<>();
    }

    public List<ResponseError> getErrors() {
        return errors;
    }

    public void setErrors(List<ResponseError> errors) {
        this.errors = errors;
    }

    public void addError(ResponseError error){
        errors.add(error);
    }

    public void addError(String message){
        errors.add(new ResponseError(message));
    }

    public void addError(String message, String details){
        errors.add(new DetailedResponseError(message,details));
    }
}
