package com.example.transportcompanyapplication.util;


import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class EntityValidator<T> {
    private final Validator validator;

    public EntityValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(T entity){
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
