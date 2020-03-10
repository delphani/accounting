package com.example.demo.service;

import com.example.demo.da.AccountDA;
import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */
@Service
public class AccountService implements ServiceInterface<Account> {

    @Autowired
    private AccountDA accountDA;

    @Override
    public Account save(Account entity) {
        try {
            return accountDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Account entity) {
        try {
            accountDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Account selectById(long id) {
        try {
            return accountDA.findAccountById(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public List<Account> selectAll() {
        try {
            return accountDA.findAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delete(long id) {
        try {
            accountDA.deleteById(id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
