package com.example.demo.controller.validitor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class AccountValiditor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"customer","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"branche","NotEmpty");
    }
}
