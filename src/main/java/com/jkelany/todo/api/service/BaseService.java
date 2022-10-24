package com.jkelany.todo.api.service;

import com.jkelany.todo.api.exception.NotFoundException;

import java.util.List;

public interface BaseService<T, ID> {

    T get(ID id) throws NotFoundException;

    List<T> getAll();

    T insert(T item);

    T update(T item);

    void delete(ID id) throws NotFoundException;

}