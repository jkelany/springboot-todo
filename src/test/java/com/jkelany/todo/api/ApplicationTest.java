package com.jkelany.todo.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest {
    @Mock
    protected MessageSource messageSource;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

}
