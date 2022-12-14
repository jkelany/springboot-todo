package com.jkelany.todo.api.validator;

import com.jkelany.todo.api.validator.impl.IpAddressImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IpAddressImpl.class})
public @interface IpAddress {

    String message() default "{validation.constraint.ipaddress.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
