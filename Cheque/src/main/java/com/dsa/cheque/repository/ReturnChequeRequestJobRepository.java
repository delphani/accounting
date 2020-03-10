package com.dsa.cheque.repository;

import com.dsa.cheque.entity.ReturnChequeRequestJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ReturnChequeRequestJobRepository extends JpaRepository<ReturnChequeRequestJob, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE RETURNCHEQUECBIREQUESTJOB RCRJ " +
            " SET RCRJ.STATUS = 'P', RCRJ.WORKERID = :workerId " +
            " WHERE RCRJ.STATUS = 'E' AND RCRJ.WORKERID IS NULL AND ROWNUM <= :count")
    void rowJobLockup(@Param("workerId") String workerId, @Param("count") int count);

    List<ReturnChequeRequestJob> findByWorkerIdAndStatus(String workerId, String status);

    int countByRequestIdAndStatus(String requestId, String status);

    @Modifying
    @Query("UPDATE ReturnChequeRequestJob RCRJ SET RCRJ.sendDate=:sendDate,RCRJ.responseDate=:responseDate, RCRJ.status=:status WHERE RCRJ.id=:reqId")
    void updateStatusById(@Param("reqId") long id, @Param("status") String status, @Param("sendDate") Date sendDate, @Param("responseDate") Date responseDate);

    @Modifying
    @Query("UPDATE ReturnChequeRequestJob RCRJ SET RCRJ.status=:status WHERE RCRJ.workerId=:worker")
    void updateStatusByWorkerId(@Param("worker") String workerId, @Param("status") String status);

    List<ReturnChequeRequestJob> findByRequestIdAndStatus(String requestId, String status);
}
