package com.example.transportcompanyapplication.exceptions;

public class UniqueFieldException extends Exception{
    private String constraintName;
    private String details;
    public UniqueFieldException(String constraintName, String details) {
        super("The uniqueness constraint '" + constraintName +"' has been violated");
        this.constraintName = constraintName;
        this.details = details;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public String getDetails() {
        return details;
    }
}
