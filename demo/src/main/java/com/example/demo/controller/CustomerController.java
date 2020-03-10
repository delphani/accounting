package com.example.demo.controller;

import com.example.demo.controller.ExeptionHandeling.ObjectNotEmpty;
import com.example.demo.controller.validitor.CustomerValiditor;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.service.CustomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by abdoli on 3/9/2020.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController implements ControllerInterface<Customer> {
    @Autowired
    CustomeService customeService;
    @Autowired
    CustomerValiditor customerValiditor;

    @PostMapping("/save")
    @Override
    public void save(@RequestBody Customer entity, BindingResult bindingResult) {

        customerValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
           throw  new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        customeService.save(entity);

    }


    @PostMapping("/update")
    @Override
    public void update(@RequestBody Customer entity, BindingResult bindingResult) {
        customerValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw  new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        customeService.update(entity);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(@PathVariable long id) {
        customeService.delete(id);
    }

    @GetMapping("/findAll")
    @Override
    public List<Customer> findAll() {
        return customeService.selectAll();
    }

    @GetMapping("/{id}")
    @Override
    public Customer findById(@PathVariable Long id) {
        return new Customer();
    }
}
