package com.dsa.cheque.client;

import com.dsa.SpringBootWebApplication;
import com.dsa.cheque.client.returncheque.IChequeService;
import com.dsa.cheque.client.returncheque.StrResponse;
import com.dsa.cheque.utiles.ReturnChequeServiceFactory;
import com.dsa.core.exception.CoreException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Company DSA
 *
 * @author A.Fallah
 * Date: 1/7/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class})
public class RejectChequeServiceTest {

    @Autowired
    ReturnChequeServiceFactory serviceFactory;

    @Test
    public void testService() throws CoreException {
        String recordNew = "1398101778100182                    ����� ����                    4111319677 10101018683   000000000005678013640424100        0510011104070707361100014977800000006978150001398101513981015IRR000001";

        try {
            IChequeService chequeService=serviceFactory.create();
            StrResponse strResponse=chequeService.sendChequeNewRecord(recordNew);
            if (strResponse != null)
                System.out.println("... recordNew >>" + strResponse.getReturnValue());
                System.out.println("... HasError >>" + strResponse.getHasError());
        } catch (CoreException ex) {
            ex.printStackTrace();
            throw new CoreException(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CoreException(ex);
        }
    }
}
