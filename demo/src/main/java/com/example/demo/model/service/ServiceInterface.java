package com.example.demo.model.service;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */

public interface ServiceInterface<T> {
    public T save(T entity);
    public void update(T entity);
    public T selectById(long id) throws Exception;
    public List<T> selectAll();
    public void delete(long id);


}
