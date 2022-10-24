package com.jkelany.todo.api.service;

import com.jkelany.todo.api.cdm.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService extends BaseService<Todo, String> {

    Page<Todo> getAll(String userId, Pageable pageable);
}
