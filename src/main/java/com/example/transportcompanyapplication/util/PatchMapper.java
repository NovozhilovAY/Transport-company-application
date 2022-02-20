package com.example.transportcompanyapplication.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;

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
            field.setAccessible(true);
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(fieldValue!=null){
                ReflectionUtils.setField(field, target, fieldValue);
            }
        }
        validator.validate(target);
    }
}
