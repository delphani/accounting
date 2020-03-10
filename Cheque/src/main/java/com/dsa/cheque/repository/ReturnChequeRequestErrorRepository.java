package com.dsa.cheque.repository;

import com.dsa.cheque.entity.ReturnChequeRequestError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ReturnChequeRequestErrorRepository extends JpaRepository<ReturnChequeRequestError, Long> {
}
