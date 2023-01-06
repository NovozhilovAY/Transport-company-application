package com.example.transportcompanyapplication.service.api;

import java.util.List;

public interface AbstractService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(T entity, ID id);

    T partialUpdate(T entity, ID id);

    void deleteById(ID id);
}
