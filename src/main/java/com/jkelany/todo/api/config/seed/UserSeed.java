package com.jkelany.todo.api.config.seed;

import com.jkelany.todo.api.cdm.entity.User;
import com.jkelany.todo.api.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserSeed implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (!userService.hasUsers()) {
            log.info("Create default users");

            User user = new User("Mahmoud Kelany", "jkelany", "123456");
            userService.save(user);
        }
    }

}
