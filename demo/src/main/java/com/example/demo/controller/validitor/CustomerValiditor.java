package com.example.demo.controller.validitor;

import com.example.demo.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by abdoli on 3/9/2020.
 */
@Component
public class CustomerValiditor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"family","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"natinalcode","NotEmpty");
        ValidationUtils.rejectIfEmpty(errors,"age","NotEmpty");
        Customer customer = (Customer) o;
        if(customer.getAge() < 18 )
            errors.reject("age cannot  be less than 18 !!!");
    }
}
