package com.example.demo.service;

import com.example.demo.da.BrancheDA;
import com.example.demo.entity.Branche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */
@Service
public class BranchService implements ServiceInterface<Branche> {
    @Autowired
    BrancheDA  brancheDA;

    @Override
    public Branche save(Branche entity) {
        try {
           return brancheDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Branche entity) {
        try {
            brancheDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Branche selectById(long id) {
        try {
            return brancheDA.findBrancheById(id);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Branche> selectAll() {
        try {
            return brancheDA.findAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(long id) {
        try {
            brancheDA.deleteById(id);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
