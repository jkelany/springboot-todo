package com.jkelany.todo.api.exception.base;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiCustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Map<String, Object> rs = new HashMap<>();
        rs.put("timestamp", errorAttributes.get("timestamp"));
        rs.put("code", errorAttributes.get("status"));
        rs.put("message", errorAttributes.get("message"));
        rs.put("errors", Arrays.asList(errorAttributes.get("error")));

        return rs;
    }

}
