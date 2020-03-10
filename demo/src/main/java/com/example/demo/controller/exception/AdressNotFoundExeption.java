package com.example.demo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by abdoli on 3/8/2020.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdressNotFoundExeption extends RuntimeException {
    public AdressNotFoundExeption(String exeption)
    {
        super(exeption);
    }
}
