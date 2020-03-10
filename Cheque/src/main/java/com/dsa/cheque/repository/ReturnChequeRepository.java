package com.dsa.cheque.repository;

import com.dsa.cheque.entity.ReturnChequeCbi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ReturnChequeRepository extends JpaRepository<ReturnChequeCbi, String> {
    @Query(nativeQuery = true, value = "SELECT TFSF_RETURNCHEQUECBITEXT(:reqId) FROM dual")
    String generateReturnChequeText(@Param("reqId") String requestId);

    ReturnChequeCbi findByRequestId(String requestId);

    @Modifying
    @Query("update ReturnChequeCbi rcc set rcc.rejectionRefNo = :rejCode where rcc.requestId = :reqSendId")
    void updateRejectionRefNo(@Param("reqSendId") String requestId, @Param("rejCode") String refNo);
}
