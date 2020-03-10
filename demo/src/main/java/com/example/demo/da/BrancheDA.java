package com.example.demo.da;

import com.example.demo.entity.Branche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdoli on 3/2/2020.
 */
@Repository
public interface BrancheDA extends JpaRepository<Branche,Long> {
    Branche findBrancheById(long id);
}
