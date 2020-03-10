package com.example.demo.controller;

import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */
public interface ControllerInterface<T> {
    public void save(T entity, BindingResult bindingResult);
    public void update(T entity, BindingResult bindingResult);
    public void delete(long id);
    public List<T> findAll();
    public T findById(Long id);
}
