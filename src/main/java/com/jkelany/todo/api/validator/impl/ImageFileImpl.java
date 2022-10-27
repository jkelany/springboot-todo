package com.jkelany.todo.api.validator.impl;

import com.jkelany.todo.api.validator.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageFileImpl implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null) {
            return false;
        } else if (!isSupportedContentType(multipartFile.getContentType())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only PNG or JPG images are allowed.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType != null
                && (contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg"));
    }
}
