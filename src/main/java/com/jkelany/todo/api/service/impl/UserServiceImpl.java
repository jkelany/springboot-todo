package com.jkelany.todo.api.service.impl;

import com.jkelany.todo.api.cdm.entity.User;
import com.jkelany.todo.api.repository.UserRepository;
import com.jkelany.todo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User save(User item) {
        item.setPassword(passwordEncoder().encode(item.getPassword()));
        return userRepository.save(item);
    }

    @Override
    public boolean hasUsers() {
        return userRepository.count() > 0;
    }

}
