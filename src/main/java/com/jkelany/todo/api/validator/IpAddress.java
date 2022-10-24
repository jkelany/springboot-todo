package com.jkelany.todo.api.validator;

import com.jkelany.todo.api.validator.impl.IpAddressImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {IpAddressImpl.class})
public @interface IpAddress {

    String message() default "{validation.constraint.ipaddress.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
