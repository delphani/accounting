package com.example.demo.model.da;

import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdoli on 2/27/2020.
 */
@Repository
public interface UserDA extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
