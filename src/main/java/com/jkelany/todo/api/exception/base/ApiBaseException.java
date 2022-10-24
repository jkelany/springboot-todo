package com.jkelany.todo.api.exception.base;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;

@StandardException
public abstract class ApiBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public abstract HttpStatus getHttpStatus();

}
