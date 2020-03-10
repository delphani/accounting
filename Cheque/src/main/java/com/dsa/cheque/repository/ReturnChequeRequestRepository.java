package com.dsa.cheque.repository;

import com.dsa.cheque.entity.ReturnChequeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ReturnChequeRequestRepository extends JpaRepository<ReturnChequeRequest, Long> {
    ReturnChequeRequest findByRequestIdAndId(String requestId, long id);

    @Modifying
    @Query("update ReturnChequeRequest rc set rc.status = :status where rc.id = :reqId")
    void updateStatus(@Param("reqId") long reqId, @Param("status") String status);

    ReturnChequeRequest findTop1ByRequestIdAndStatusOrderByIdDesc(String requestId, String status);
}
