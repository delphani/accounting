package com.example.demo.controller.ExeptionHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by abdoli on 3/9/2020.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectNotEmpty extends RuntimeException {
   public ObjectNotEmpty(String exception)
   {
       super(exception);
   }
}
