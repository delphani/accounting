package com.example.demo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by abdoli on 3/8/2020.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdressNotEmpty extends RuntimeException {
    public AdressNotEmpty(String exeption    )
    {
        super(exeption);
    }
}
