package com.jkelany.todo.api.repository;

import com.jkelany.todo.api.cdm.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {

    User findByUsername(String username);
}
