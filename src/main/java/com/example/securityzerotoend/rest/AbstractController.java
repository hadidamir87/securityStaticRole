package com.example.securityzerotoend.rest;

import com.example.securityzerotoend.model.convertor.AbstractConvertor;
import com.example.securityzerotoend.model.entity.AbstractEntity;
import com.example.securityzerotoend.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


public class AbstractController<E extends AbstractEntity,D,SR,S extends AbstractService<E,?extends JpaRepository<E,Long>>>{
    @Autowired
    protected S service;
    @Autowired
    protected AbstractConvertor<E,D,SR> convertor;
}
