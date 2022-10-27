package com.jkelany.todo.api.validator;

import com.jkelany.todo.api.validator.impl.ImageFileImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFileImpl.class})
public @interface ImageFile {
    String message() default "{validation.constraint.imagefile.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
