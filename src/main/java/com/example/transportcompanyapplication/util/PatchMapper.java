package com.example.transportcompanyapplication.util;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.model.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
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
            new PropertyDescriptor(field.getName(), target.getClass()).getWriteMethod().invoke(target, castValue(field, value));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    private Object castValue(Field field, Object value){
        if(value == null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        if(!(value instanceof Collection)){
            return objectMapper.convertValue(value, field.getType());
        }else {
            ParameterizedType type = (ParameterizedType)field.getGenericType();
            Class<?> genericClazz = (Class<?>)type.getActualTypeArguments()[0];
            JavaType collectionType = objectMapper.getTypeFactory()
                    .constructCollectionType((Class<? extends Collection>) field.getType(), genericClazz);
            return objectMapper.convertValue(value, collectionType);
        }
    }
}
