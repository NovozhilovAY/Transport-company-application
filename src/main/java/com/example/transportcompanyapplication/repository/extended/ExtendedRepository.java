package com.example.transportcompanyapplication.repository.extended;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedRepository<T,ID> extends JpaRepository<T, ID> {
    void update(T entity);
    void insert(T entity);
}
