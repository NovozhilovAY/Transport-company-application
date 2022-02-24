package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import com.example.transportcompanyapplication.util.PatchMapper;


import java.util.List;


public abstract class AbstractService<T, ID> {

    protected final ExtendedRepository<T, ID> repository;
    protected final PatchMapper<T> mapper;


    protected AbstractService(ExtendedRepository<T, ID> repository, PatchMapper<T> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findById(ID id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Resource with id = "+ id +" not found")
        );
    }

    public T save(T entity){
        return repository.save(entity);
    }

    public T update(T entity, ID id){
        this.findById(id);
        repository.update(entity);
        return findById(id);
    }

    public T partialUpdate(T entity, ID id){
        T updatedEntity = findById(id);
        mapper.update(entity, updatedEntity);
        return this.update(updatedEntity,id);
    }

    public void deleteById(ID id){
        repository.deleteById(id);
    }


}
