package com.dsa.cheque.ws.endpoint;

import com.dsa.cheque.config.WsConfig;
import com.dsa.cheque.service.ReturnChequeService;
import com.dsa.cheque.utiles.LogUtil;
import com.dsa.cheque.ws.model.reponse.ResponseError;
import com.dsa.cheque.ws.service.ReturnChequeClientService;
import com.dsa.cheque.ws.model.reponse.ReturnChequeInstantResponse;

import com.dsa.cheque.ws.model.request.ReturnChequeInstantRequest;
import com.dsa.core.exception.CoreException;
import com.dsa.core.log.ObjectLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ReturnChequeInstantServiceImpl {
    public static final String REJECT_LOCAL_PART = "returnChequeInstantRequest";

    private static final Logger logger = LoggerFactory.getLogger(ReturnChequeClientService.class);
    private ReturnChequeClientService clientService;

    @Autowired
    public ReturnChequeInstantServiceImpl(ReturnChequeClientService clientService) {
        this.clientService = clientService;
    }

    @PayloadRoot(namespace = WsConfig.CHEQUE_SERVICE_SCHEMA_NAMESPACE, localPart = REJECT_LOCAL_PART)
    @ResponsePayload
    public ReturnChequeInstantResponse reject(@RequestPayload ReturnChequeInstantRequest request) {
        ReturnChequeInstantResponse response = new ReturnChequeInstantResponse();
        try {
            response = clientService.getRejectTraceCode(request);
        } catch (CoreException e) {
            logger.error(LogUtil.LOG_REQUEST_ERROR);
            ObjectLogger.log(request);
            logger.error(e.getMessage());
            response.setHasError(true);
            response.setErrors(ResponseError.createErrors(LogUtil.CODE_SERVICE_INVALID_PARAM_FORMAT, e.getErrorMessage()));
        } catch (InvalidDataAccessResourceUsageException e) {
            logger.error(e.getMessage());
            response.setHasError(true);
            response.setErrors(ResponseError.createErrors(LogUtil.CODE_SERVICE_INVALID_PARAM_FORMAT,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_INVALID_PARAM_FORMAT)));
        } catch (Exception e) {
            e.printStackTrace();
            response.setHasError(true);
            response.setErrors(ResponseError.createErrors(LogUtil.CODE_SERVICE_INTERNAL_ERROR,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_INTERNAL_ERROR)));
        }
        return response;
    }
}
