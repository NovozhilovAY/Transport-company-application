package com.example.transportcompanyapplication.util;

import org.springframework.stereotype.Component;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Component
public class PatchMapper<T> {
    private final EntityValidator<T> validator;

    public PatchMapper(EntityValidator<T> validator) {
        this.validator = validator;
    }

    public void update(T object, T target){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            Object fieldValue = null;
            try {
                fieldValue = new PropertyDescriptor(field.getName(), object.getClass()).getReadMethod().invoke(object);
                if(fieldValue!=null){
                    new PropertyDescriptor(field.getName(),object.getClass()).getWriteMethod().invoke(target, fieldValue);
                }
            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                e.printStackTrace();
            }
        }
        validator.validate(target);
    }
}
