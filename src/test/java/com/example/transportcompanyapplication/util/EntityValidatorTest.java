package com.example.transportcompanyapplication.util;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EntityValidator.class})
@ExtendWith(SpringExtension.class)
class EntityValidatorTest {
    @Autowired
    private EntityValidator<Object> entityValidator;

    @MockBean
    private Validator validator;

    /**
     * Method under test: {@link EntityValidator#validate(Object)}
     */
    @Test
    void testValidate() {
        when(validator.validate((String) any(), (Class[]) any())).thenReturn(new HashSet<>());
        entityValidator.validate("Entity");
        verify(validator).validate((String) any(), (Class[]) any());
    }

    /**
     * Method under test: {@link EntityValidator#validate(Object)}
     */
    @Test
    void testValidate2() {
        HashSet<ConstraintViolation<String>> constraintViolationSet = new HashSet<>();
        constraintViolationSet.add(null);
        when(validator.validate((String) any(), (Class[]) any())).thenReturn(constraintViolationSet);
        assertThrows(ConstraintViolationException.class, () -> entityValidator.validate("Entity"));
        verify(validator).validate((String) any(), (Class[]) any());
    }

    /**
     * Method under test: {@link EntityValidator#validate(Object)}
     */
    @Test
    void testValidate3() {
        when(validator.validate((String) any(), (Class[]) any()))
                .thenThrow(new ConstraintViolationException(new HashSet<>()));
        assertThrows(ConstraintViolationException.class, () -> entityValidator.validate("Entity"));
        verify(validator).validate((String) any(), (Class[]) any());
    }
}

