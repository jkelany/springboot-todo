package com.jkelany.todo.api.service;

import com.jkelany.todo.api.cdm.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User item);

    boolean hasUsers();
}
