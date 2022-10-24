package com.jkelany.todo.api.exception.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private Date timestamp = new Date();
    private int code;
    private String message;
    private Map<String, List<Object>> errors = new HashMap();
    private List<String> details = new ArrayList<>();

    public ApiError(HttpStatus code) {
        this(code, code.name().toLowerCase());
    }

    public ApiError(HttpStatus code, String message) {
        this.code = code.value();
        this.message = message;
    }

    public void addError(String key, String value) {
        if (this.errors.containsKey(key)) {
            this.errors.get(key).add(value);
        } else {
            this.errors.put(key, Arrays.asList(value));
        }
    }

    public void addDetails(String message) {
        this.details.add(message);
    }
}
