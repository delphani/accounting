package com.example.demo.da;

import com.example.demo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abdoli on 2/27/2020.
 */
public interface RoleDA extends JpaRepository<Roles,Long> {
}
