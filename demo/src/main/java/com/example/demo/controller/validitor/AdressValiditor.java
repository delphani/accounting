package com.example.demo.controller.validitor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by abdoli on 3/2/2020.
 */

@Component
public class AdressValiditor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors,"province","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"city","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"street","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"pelak","NotEmpty");


    }
}
