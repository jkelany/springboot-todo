package com.jkelany.todo.api.exception;

import com.jkelany.todo.api.exception.base.ApiBaseException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;

@StandardException
public class UnauthorizedException extends ApiBaseException {

    private static final long serialVersionUID = 1L;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
