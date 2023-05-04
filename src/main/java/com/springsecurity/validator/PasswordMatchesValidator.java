package com.springsecurity.validator;

import com.springsecurity.dto.SignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordValidator, SignUpRequest> {
    @Override
    public boolean isValid(SignUpRequest value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getMatchingPassword());
    }
}
