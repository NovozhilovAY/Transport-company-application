package com.example.transportcompanyapplication.repository.extended;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ExtendedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {
    @PersistenceContext
    EntityManager entityManager;

    public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public ExtendedRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }


    @Transactional
    @Override
    public void update(T entity) {
        Session session = (Session)entityManager.getDelegate();
        session.update(entity);
        session.flush();
        session.clear();
    }

    @Transactional
    @Override
    public void insert(T entity) {
        Session session = (Session)entityManager.getDelegate();
        session.save(entity);
        session.flush();
        session.clear();
    }
}
