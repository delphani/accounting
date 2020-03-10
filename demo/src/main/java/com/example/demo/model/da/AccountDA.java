package com.example.demo.model.da;

import com.example.demo.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdoli on 3/2/2020.
 */
@Repository
public interface AccountDA extends JpaRepository<Account,Long> {
    Account findAccountById(long id);
}
