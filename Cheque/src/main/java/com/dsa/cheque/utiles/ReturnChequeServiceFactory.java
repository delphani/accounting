package com.dsa.cheque.utiles;

import com.dsa.cheque.client.returncheque.ChequeService;
import com.dsa.cheque.client.returncheque.ChequeServiceLocator;
import com.dsa.cheque.client.returncheque.IChequeService;
import com.dsa.core.exception.CoreException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ReturnChequeServiceFactory {
    @Value("${cbi.returncheque.service.url}") private String cbiReturnChequeUrl;
    @Value("${cbi.returncheque.service.user}") private String serviceUser;
    @Value("${cbi.returncheque.service.pass}") private String servicePassword;
    @Value("${cbi.returncheque.service.timeout}") private int serviceTimeout;

    public IChequeService create() {
        try {
            return new ChequeServiceLocator().getBasicHttpBinding_IChequeService(new URL(this.cbiReturnChequeUrl),
                    this.serviceUser,
                    this.servicePassword, this.serviceTimeout);
        } catch (Exception ex) {
            throw new CoreException(ex);
        }
    }
}
