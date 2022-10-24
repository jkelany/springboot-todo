package com.jkelany.todo.api.repository;

import com.jkelany.todo.api.cdm.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends BaseRepository<Todo, String> {

    Page<Todo> findByUserId(String userId, Pageable pageable);
}
