package com.dsa.cheque.repository;

import com.dsa.cheque.entity.ReturnChequeCbiError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ReturnChequeErrorRepository extends JpaRepository<ReturnChequeCbiError, String> {
    ReturnChequeCbiError findByCode(String code);
}
