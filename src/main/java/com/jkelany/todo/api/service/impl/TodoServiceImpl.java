package com.jkelany.todo.api.service.impl;

import com.jkelany.todo.api.cdm.entity.Todo;
import com.jkelany.todo.api.exception.NotFoundException;
import com.jkelany.todo.api.repository.TodoRepository;
import com.jkelany.todo.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl extends BaseServiceImpl<Todo, String> implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Page<Todo> getAll(String userId, Pageable pageable) {
        return todoRepository.findByUserId(userId, pageable);
    }

    @Override
    @Cacheable(value = "todoGet", key = "#id")
    public Todo get(String id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    @CacheEvict(value = {"todoGet"}, key = "#item.id")
    public Todo insert(Todo item) {
        return super.insert(item);
    }

    @Override
    @CacheEvict(value = {"todoGet"}, key = "#item.id")
    public Todo update(Todo item) {
        return super.update(item);
    }

    @Override
    //example for adding multiple evict, put, cacheable
    @Caching(evict = {@CacheEvict(value = {"todoGet"}, key = "#id")})
    public void delete(String id) throws NotFoundException {
        super.delete(id);
    }
}
