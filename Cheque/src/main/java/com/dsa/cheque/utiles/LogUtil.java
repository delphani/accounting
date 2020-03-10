package com.dsa.cheque.utiles;

public class LogUtil {
    public static final String CODE_SERVICE_NOT_AVILABLE_ERROR = "900";
    public static final String CODE_SERVICE_NOT_EMPTY_PARAM = "901";
    public static final String CODE_CBI_DUPLICATE_REQUEST = "902";
    public static final String CODE_SERVICE_INVALID_REQUEST_ID = "903";
    public static final String CODE_SERVICE_INVALID_PARAM_FORMAT = "904";
    public static final String CODE_SERVICE_INTERNAL_ERROR = "905";

    public static final String EXCEPTION_FORMAT_DESC_ERROR = "Invalid request id : %s";
    public static final String EXCEPTION_ALREADY_DESC_ERROR = "Return cheque info already sent";
    public static final String EXCEPTION_INTERNAL_ERROR = "Internal Error";
    public static final String EXCEPTION_BANK_ID_EMPTY = "Bank code should not be empty";
    public static final String EXCEPTION_BRANCH_ID_EMPTY = "Branch code should not be empty";
    public static final String EXCEPTION_REQUEST_ID_EMPTY = "Request id should not be empty";
    public static final String EXCEPTION_USER_CODE_EMPTY = "User code should not be empty";
    public static final String EXCEPTION_INVALID_PARAM_FORMAT = "Invalid request param format";

    public static final String MSG_CBI_SERVICE_NOT_AVAILABLE = "Cbi service is not available";

    public static final String LOG_REQUEST_ERROR = "New request : error in param";
    public static final String LOG_REQUEST = "New request : [branch:%s, user:%s, requestId:%s]";
    public static final String LOG_FETCH_REQUEST = "Fetch last request : [requestId=%s, refId=%s]";
    public static final String LOG_RETURN_CHEQUE_REC = "ReturnChequeRec : %s";

    public static final String PROP_ERROR = "Error";
    public static final String PROP_CBI_ERROR = "CBI Error";
    public static final String PROP_SUCCESS = "Success";

    public static final String JOB_NEW_REQUEST = ">>>>>>>>>> Send new request [%s] <<<<<<<<<<";
    public static final String JOB_REQUEST_VALUE = "Request value [%s] : %s";
    public static final String JOB_GET_TRACE_CODE= "Get trace code for [%s] : %s";
    public static final String JOB_CREATE_NEW_REQUEST_FOR_TRY_AGAIN= "Create new request for try again [%s]";
    public static String getLogVal(String key,Object... arg) {
        return String.format(key, arg);
    }
}
