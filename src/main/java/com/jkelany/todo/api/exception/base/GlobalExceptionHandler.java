package com.jkelany.todo.api.exception.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.BAD_REQUEST, "Method argument not valid");
        for (FieldError error : ex.getFieldErrors()) {
            customErrorDetails.addError(error.getField(), error.getDefaultMessage());
        }

        for (ObjectError error : ex.getGlobalErrors()) {
            customErrorDetails.addError(error.getObjectName(), error.getDefaultMessage());
        }

        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                           WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        customErrorDetails.addDetails(request.toString());
        return new ResponseEntity<>(customErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError customErrorDetails = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getMessage());
        customErrorDetails.addDetails(request.toString());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
