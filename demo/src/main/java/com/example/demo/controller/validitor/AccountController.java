package com.example.demo.controller.validitor;

import com.example.demo.controller.ControllerInterface;
import com.example.demo.controller.ExeptionHandeling.ObjectNotEmpty;
import com.example.demo.model.entity.Account;
import com.example.demo.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/account")
public class AccountController implements ControllerInterface<Account> {

    @Autowired
    private AccountService accountService ;
    @Autowired
    private AccountValiditor accountValiditor ;

    @PostMapping("/save")
    @Override
    public void save(@RequestBody  Account entity, BindingResult bindingResult) {
        accountValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        accountService.save(entity);

    }

    @PostMapping("/update")
    @Override
    public void update(@RequestBody Account entity, BindingResult bindingResult) {
        accountValiditor.validate(entity,bindingResult);
        if(bindingResult.hasErrors())
            throw new ObjectNotEmpty(bindingResult.getAllErrors().toString());
        accountService.save(entity);

    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void delete(@PathVariable  long id) {
        accountService.delete(id);
    }

    @GetMapping("/findAll")
    @Override
    public List<Account> findAll() {
        return accountService.selectAll();
    }

    @GetMapping("/{id}")
    @Override
    public Account findById(Long id) {
        return accountService.selectById(id);
    }
}
