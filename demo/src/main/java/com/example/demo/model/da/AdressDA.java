package com.example.demo.model.da;

import com.example.demo.model.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdoli on 3/2/2020.
 */
@Repository
public interface AdressDA extends JpaRepository<Adress,Long> {

    Adress findAdressesById(long id);
}
