package com.example.demo.model.service;

import com.example.demo.model.da.AdressDA;
import com.example.demo.model.entity.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdoli on 3/2/2020.
 */
@Service
public class AdressService implements ServiceInterface<Adress> {

    @Autowired
    AdressDA adressDA;

    @Override
    public Adress save(Adress entity) {
        try {
           return adressDA.save(entity);

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Adress entity) {
        try {
            adressDA.save(entity);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Adress selectById(long id) {
        try {

                return adressDA.findAdressesById(id);
        }catch (Exception e)
        {
            return null;
        }


    }

    @Override
    public List<Adress> selectAll() {
        try {
            return  adressDA.findAll();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delete(long id) {
        try {
            adressDA.deleteById(id);
        }catch (Exception e)
        {
            e.printStackTrace();

        }

    }
}
