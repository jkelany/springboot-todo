package com.jkelany.todo.api.controller;

import com.jkelany.todo.api.exception.base.ApiError;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiErrorController implements ErrorController {

    @RequestMapping("/error")
    public ApiError handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int errorCode = Integer.parseInt(status.toString());
            return new ApiError(HttpStatus.resolve(errorCode));
        }
        return new ApiError(HttpStatus.NOT_FOUND);
    }
}