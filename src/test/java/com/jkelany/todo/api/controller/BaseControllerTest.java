package com.jkelany.todo.api.controller;

import com.jkelany.todo.api.ApplicationTest;
import com.jkelany.todo.api.cdm.entity.User;
import com.jkelany.todo.api.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureDataMongo
@TestPropertySource(locations = "/application-junitest.properties")
public class BaseControllerTest extends ApplicationTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected AuthService authService;

    @Override
    @BeforeEach
    public void init() {
        super.init();

        User authUser = new User();
        authUser.setId("1");
        when(authService.getCurrentUser()).thenReturn(authUser);
    }

    protected MockHttpServletRequestBuilder doGet(String url) {
        return get(url)
                .contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder doPost(String url) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder doDelete(String url) {
        return delete(url)
                .contentType(MediaType.APPLICATION_JSON);
    }
}
