package com.example.demo.controller;

import com.example.demo.controller.exception.ObjectNotEmpty;
import com.example.demo.controller.validitor.BrancheValiditor;
import com.example.demo.entity.Branche;
import com.example.demo.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by abdoli on 3/9/2020.
 */
@RestController
@RequestMapping("/branche")
public class BrancheController  implements ControllerInterface<Branche>{

    @Autowired
    BranchService branchService;
    @Autowired
    BrancheValiditor brancheValiditor;


    @PostMapping("/save")
    @Override
    public void save(@RequestBody Branche entity, BindingResult bindingResult) {
        brancheValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        branchService.save(entity);

    }

    @PostMapping("/update")
    @Override
    public void update(@RequestBody  Branche entity, BindingResult bindingResult) {
        brancheValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        branchService.update(entity);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(@PathVariable long id) {
        branchService.delete(id);
    }

    @GetMapping("/findAll")
    @Override
    public List<Branche> findAll() {
        return branchService.selectAll();
    }

    @GetMapping("/{id}")
    @Override
    public Branche findById(@PathVariable Long id) {
        return branchService.selectById(id);
    }
}
