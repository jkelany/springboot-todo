package com.jkelany.todo.api.exception.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ApiError> handleApiBaseException(ApiBaseException ex) {
        return new ResponseEntity<>(new ApiError(ex.getHttpStatus(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler({ResponseStatusException.class})
    protected ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getStatus(), ex.getMessage()), ex.getStatus());

    }

}
