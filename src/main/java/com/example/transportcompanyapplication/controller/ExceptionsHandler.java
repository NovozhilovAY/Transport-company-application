package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.exceptions.*;
import com.example.transportcompanyapplication.util.ForeignKeyExceptionMessageParser;
import com.example.transportcompanyapplication.util.UniqueExceptionMessageParser;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionsHandler {
    private final String UNIQUE_VIOLATION_CODE = "23505";
    private final String FOREIGN_KEY_VIOLATION_CODE = "23503";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrors> handleException(ResourceNotFoundException e) {
        ResponseErrors errors = new ResponseErrors();
        errors.addError(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ResponseErrors> handleDBException(PSQLException e){
        ResponseErrors errors = new ResponseErrors();
        if(UNIQUE_VIOLATION_CODE.equals(e.getSQLState())){
            UniqueFieldException ex = UniqueExceptionMessageParser.parse(e.getMessage());
            return handleConstraintsException(ex);
        }
        if (FOREIGN_KEY_VIOLATION_CODE.equals(e.getSQLState())){
            ForeignKeyException ex = ForeignKeyExceptionMessageParser.parse(e.getMessage());
            return handleForeignKeyException(ex);
        }
        errors.addError(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueFieldException.class)
    public ResponseEntity<ResponseErrors> handleConstraintsException(UniqueFieldException e) {
        ResponseErrors errors = new ResponseErrors();
        String detailsMessage = "Value " + e.getDetails() + " already exists";
        errors.addError(e.getMessage(), detailsMessage);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForeignKeyException.class)
    public ResponseEntity<ResponseErrors> handleForeignKeyException(ForeignKeyException e){
        ResponseErrors errors = new ResponseErrors();
        errors.addError(e.getMessage(), e.getDetails());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrors> handleValidationException(MethodArgumentNotValidException e) {
        ResponseErrors errors = new ResponseErrors();
        e.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String field = ((FieldError)error).getField();
                    String details = error.getDefaultMessage();
                    errors.addError("Validation error of the '" + field +"' field.", details);
                }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseErrors> handleValidationException(ConstraintViolationException e){
        ResponseErrors errors = new ResponseErrors();
        e.getConstraintViolations().forEach(
                (constraintViolation -> errors.addError("Validation error", constraintViolation.getMessage()))
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminLogicException.class)
    public ResponseEntity<ResponseErrors> handleLastAdminException(AdminLogicException e){
        ResponseErrors errors = new ResponseErrors();
        errors.addError("AdminLogicException", e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
