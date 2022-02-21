package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public abstract class AbstractService<T, K> {

    protected final JpaRepository<T, K> repository;
    protected final PatchMapper<T> mapper;


    protected AbstractService(JpaRepository<T, K> repository, PatchMapper<T> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findById(K id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Resource with id = "+ id +" not found")
        );
    }


    public T save(T entity, K id){
        return repository.saveAndFlush(entity);
    }

    public T update(T entity, K id){
        this.findById(id);
        return this.save(entity,id);
    }

    public T partialUpdate(T entity, K id){
        T updatedEntity = findById(id);
        mapper.update(entity, updatedEntity);
        return this.save(updatedEntity, id);
    }

    public void deleteById(K id){
        repository.deleteById(id);
    }


}
