package com.example.demo.service;

import com.example.demo.da.CustomerDA;
import com.example.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */
@Service
public class CustomeService implements ServiceInterface<Customer> {

    @Autowired
    CustomerDA customerDA;

    @Override
    public Customer save(Customer entity) {
        try {
            return  customerDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(Customer entity) {
        try {
            customerDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Customer selectById(long id) {
        try {
            return  customerDA.findCustomerById(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Customer> selectAll() {
        try {
            return customerDA.findAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(long id) {
        try {
            customerDA.deleteById(id);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
