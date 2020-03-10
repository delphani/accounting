package com.example.demo.controller.validitor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by abdoli on 3/9/2020.
 */
@Component
public class BrancheValiditor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"branchcode","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"adress","NotEmpty");

    }
}
