package com.dsa.cheque.service;


import com.dsa.cheque.entity.*;
import com.dsa.cheque.repository.*;
import com.dsa.core.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReturnChequeService {

    public static final String ENTRY = "E";
    public static final String SENT = "S";
    public static final String REJECTED = "R";
    public static final String PENDING = "P";
    public static final String DELETED = "D";
    public static Map<String, String> errorBase = new HashMap<>();

    @PersistenceContext
    private EntityManager em;
    private ReturnChequeRepository returnChequeRepository;
    private ReturnChequeErrorRepository errorRepository;
    private ReturnChequeRequestRepository requestRepository;
    private ReturnChequeRequestErrorRepository requestErrorRepository;
    ReturnChequeRequestJobRepository jobRepository;
    @Autowired
    private DateUtil dateUtil;

    @Autowired
    public ReturnChequeService(ReturnChequeRepository returnChequeRepository,
                               ReturnChequeRequestRepository requestRepository,
                               ReturnChequeRequestErrorRepository requestErrorRepository,
                               ReturnChequeErrorRepository errorRepository,
                               ReturnChequeRequestJobRepository jobRepository) {
        this.returnChequeRepository = returnChequeRepository;
        this.requestRepository = requestRepository;
        this.requestErrorRepository = requestErrorRepository;
        this.errorRepository = errorRepository;
        this.jobRepository = jobRepository;

        this.fetchErrorBase(errorRepository.findAll());
    }

    public String generateReturnChequeText(String requestId) {
        return returnChequeRepository.generateReturnChequeText(requestId);
    }

    public ReturnChequeRequest saveRequest(ReturnChequeRequest request) {
        return requestRepository.save(request);
    }

    public void updateRequestStatus(long reqId, String status) {
        requestRepository.updateStatus(reqId, status);
    }

    public ReturnChequeRequestError saveRequestError(ReturnChequeRequestError request) {
        return requestErrorRepository.save(request);
    }

    public ReturnChequeCbi getReturnChequeCbi(String reqId) {
        return returnChequeRepository.findByRequestId(reqId);
    }

    public void updateRejectionRefNo(String requestId, String refNo) {
        returnChequeRepository.updateRejectionRefNo(requestId, refNo);
    }

    public ReturnChequeRequest findByRequesuAndStatus(String reqId, String status) {
        return requestRepository.findTop1ByRequestIdAndStatusOrderByIdDesc(reqId, status);
    }

    public ReturnChequeCbiError findErrorBycode(String code) {
        return errorRepository.findByCode(code);
    }

    private void fetchErrorBase(List<ReturnChequeCbiError> cbiErrors) {
        for (ReturnChequeCbiError cbiError : cbiErrors) {
            errorBase.put(cbiError.getCode(), cbiError.getMsgKey());
        }
    }

    public static String fetchError(String code) {
        return errorBase.get(code);
    }

    public int countRequestJob(String requestId, String status) {
        return jobRepository.countByRequestIdAndStatus(requestId, status);
    }

    public void  addNewRequestJob(ReturnChequeRequestJob requestJob) {
        jobRepository.save(requestJob);
    }
    public List<ReturnChequeRequestJob> findByReqIdAndStatus(String requestId, String status) {
        return jobRepository.findByRequestIdAndStatus(requestId, status);
    }

    @Transactional(rollbackFor = Exception.class)
    public void generateRequestForReturnChequeFromClr() {

        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("DMSP_GETCBINO_CLRRETURNCHQ");
        storedProcedureQuery.registerStoredProcedureParameter("inpcurrentdate", java.sql.Date.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("inpcurrentdate", dateUtil.getGregorianCurrentDate());
        storedProcedureQuery.execute();

    }
}
