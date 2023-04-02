package com.example.transportcompanyapplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.transportcompanyapplication.exceptions.DetailedResponseError;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.exceptions.ResponseError;
import com.example.transportcompanyapplication.exceptions.ResponseErrors;
import com.example.transportcompanyapplication.exceptions.UniqueFieldException;

import java.util.HashSet;

import java.util.List;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionsHandlerTest {
    /**
     * Method under test: {@link ExceptionsHandler#handleException(ResourceNotFoundException)}
     */
    @Test
    void testHandleException() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ResponseEntity<ResponseErrors> actualHandleExceptionResult = exceptionsHandler
                .handleException(new ResourceNotFoundException("An error occurred"));
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleExceptionResult.getStatusCode());
        List<ResponseError> errors = actualHandleExceptionResult.getBody().getErrors();
        assertEquals(1, errors.size());
        assertEquals("An error occurred", errors.get(0).getMessage());
    }

    /**
     * Method under test: {@link ExceptionsHandler#handleDBException(PSQLException)}
     */
    @Test
    void testHandleDBException() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ResponseEntity<ResponseErrors> actualHandleDBExceptionResult = exceptionsHandler
                .handleDBException(new PSQLException("Msg", PSQLState.UNKNOWN_STATE));
        assertTrue(actualHandleDBExceptionResult.hasBody());
        assertTrue(actualHandleDBExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleDBExceptionResult.getStatusCode());
        List<ResponseError> errors = actualHandleDBExceptionResult.getBody().getErrors();
        assertEquals(1, errors.size());
        assertEquals("Msg", errors.get(0).getMessage());
    }

    /**
     * Method under test: {@link ExceptionsHandler#handleConstraintsException(UniqueFieldException)}
     */
    @Test
    void testHandleConstraintsException() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ResponseEntity<ResponseErrors> actualHandleConstraintsExceptionResult = exceptionsHandler
                .handleConstraintsException(new UniqueFieldException("Constraint Name", "Details"));
        assertTrue(actualHandleConstraintsExceptionResult.hasBody());
        assertTrue(actualHandleConstraintsExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleConstraintsExceptionResult.getStatusCode());
        List<ResponseError> errors = actualHandleConstraintsExceptionResult.getBody().getErrors();
        assertEquals(1, errors.size());
        ResponseError getResult = errors.get(0);
        assertEquals("Value Details already exists", ((DetailedResponseError) getResult).getDetails());
        assertEquals("The uniqueness constraint 'Constraint Name' has been violated", getResult.getMessage());
    }

    /**
     * Method under test: {@link ExceptionsHandler#handleValidationException(ConstraintViolationException)}
     */
    @Test
    void testHandleValidationException() {
        ExceptionsHandler exceptionsHandler = new ExceptionsHandler();
        ResponseEntity<ResponseErrors> actualHandleValidationExceptionResult = exceptionsHandler
                .handleValidationException(new ConstraintViolationException(new HashSet<>()));
        assertTrue(actualHandleValidationExceptionResult.hasBody());
        assertTrue(actualHandleValidationExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleValidationExceptionResult.getStatusCode());
        assertTrue(actualHandleValidationExceptionResult.getBody().getErrors().isEmpty());
    }
}

