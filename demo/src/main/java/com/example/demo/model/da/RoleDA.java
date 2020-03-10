package com.example.demo.model.da;

import com.example.demo.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by abdoli on 2/27/2020.
 */
public interface RoleDA extends JpaRepository<Roles,Long> {
}
