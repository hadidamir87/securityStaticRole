package com.example.securityzerotoend.service;


import com.example.securityzerotoend.exceptionhandlers.exceptions.ServiceException;
import com.example.securityzerotoend.model.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends JpaRepository<E, Long>> {

    @Autowired
    protected R repository;

    public void add(E e)  throws ServiceException {
        repository.save(e);
    }

    public void update(E e) throws ServiceException {
        repository.save(e);
    }

    public Optional<E> findById(Long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RuntimeException e) {
            throw new ServiceException("entity-not-found");
        }
    }

    public abstract E updateById(E e) throws Exception;

    public E getById(Long id) throws ServiceException {
        return repository.getReferenceById(id);
    }

    public List<E> getAll() throws Exception {
        return repository.findAll();
    }

}
