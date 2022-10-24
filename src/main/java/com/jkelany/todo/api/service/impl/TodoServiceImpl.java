package com.jkelany.todo.api.service.impl;

import com.jkelany.todo.api.cdm.entity.Todo;
import com.jkelany.todo.api.repository.TodoRepository;
import com.jkelany.todo.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
