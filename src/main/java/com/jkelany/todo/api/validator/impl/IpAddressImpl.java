package com.jkelany.todo.api.validator.impl;

import com.jkelany.todo.api.validator.IpAddress;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressImpl implements ConstraintValidator<IpAddress, String> {

    private static final String IPV4_PATTERN =
            "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty() || value.isBlank()) {
            return false;
        }

        Pattern pattern = Pattern.compile(IPV4_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
