package com.jkelany.todo.api.controller;

import com.jkelany.todo.api.cdm.response.PageResponse;
import com.jkelany.todo.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;

public class BaseController {

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected AuthService authService;

    protected <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalPages(),
                page.getTotalElements(), page.getContent());
    }

}
