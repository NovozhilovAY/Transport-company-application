package com.example.transportcompanyapplication.util;

import org.springframework.stereotype.Component;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Component
public class PatchMapper<T> {
    private final EntityValidator<T> validator;

    public PatchMapper(EntityValidator<T> validator) {
        this.validator = validator;
    }

    public void update(Map<String, Object> source, T target){
        Field[] fields = target.getClass().getDeclaredFields();
        for(Map.Entry<String, Object> entry : source.entrySet()){
            Field fieldToChange = getFieldByName(entry.getKey(), fields);
            if(fieldToChange != null){
                this.setValue(target, fieldToChange, entry.getValue());
            }
        }
        validator.validate(target);
    }

    private Field getFieldByName(String fieldName, Field[] fields){
        for (Field field:fields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        return null;
    }

    private void setValue(Object target, Field field, Object value){
        try {
            if(value == null){
                new PropertyDescriptor(field.getName(), target.getClass()).getWriteMethod().invoke(target, value);
            }else {
                if(field.getType().isAssignableFrom(value.getClass())){
                    new PropertyDescriptor(field.getName(), target.getClass()).getWriteMethod().invoke(target, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

}
