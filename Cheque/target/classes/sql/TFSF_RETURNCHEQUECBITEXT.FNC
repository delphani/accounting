CREATE OR REPLACE FUNCTION tfsf_returnChequeCBIText (inprequestId VARCHAR2)
   RETURN VARCHAR2
IS
   v_res   VARCHAR2 (300);
BEGIN
   SELECT    TO_CHAR (SYSDATE, 'YYYYMMDD', 'nls_calendar= persian')
          || LPAD (NVL (a.bankCode, '0'), 2, ' ')
          || LPAD (DMSF_BRANCHCHECKDIGIT (NVL (a.branchCode, '9999')),
                   5,
                   ' ')
          || DECODE (NVL (a.personalitycode, 'O'), 'I', 1, 2)
          || LPAD (SUBSTR (NVL (a.lastNameCompanyName, ' '), 1, 30), 30, ' ')
          || LPAD (SUBSTR (NVL (a.firstname, ' '), 1, 20), 20, ' ')
          || LPAD (NVL (a.nationalId, ' '), 10, ' ')
          || LPAD (SUBSTR (NVL (a.corporateresidenceid, ' '), 1, 12),
                   12,
                   ' ')
          || RPAD (
                NVL (
                   (SELECT RC.LONGDESC
                      FROM RANGECONSTRAINTS RC
                     WHERE     RC.TABLENAME LIKE 'CUSTOMER'
                           AND RC.COLUMNNAME LIKE 'SERIE'
                           AND SHORTDESC =
                                  (SELECT SUBSTR (serie, 1, 3)
                                     FROM NATIONALIDDTLS aa, CUSTOMER bb
                                    WHERE     Aa.CUSTOMERID = Bb.CUSTOMERID
                                          AND Aa.NATIONALIDNO = a.nationalid
                                          AND STATUS = 'A')),
                   NVL (
                      (SELECT serie
                         FROM NATIONALIDDTLS aa, CUSTOMER bb
                        WHERE     Aa.CUSTOMERID = Bb.CUSTOMERID
                              AND Aa.NATIONALIDNO = a.nationalid
                              AND STATUS = 'A'),
                      ' ')),
                3,
                ' ')
          || LPAD (NVL (a.idcardserial, '0'), 6, '0')
          || LPAD (NVL (a.registernumberidnumber, 0), 10, '0')
          || CASE
                WHEN a.registerdtbirthdt IS NOT NULL
                THEN
                   TO_CHAR (a.registerdtbirthdt,
                            'YYYYMMDD',
                            'nls_calendar= persian')
                ELSE
                   RPAD (' ', 8, ' ')
             END
          || LPAD (NVL (a.codeidcardplaceissue, ' '), 3, ' ')
          || LPAD (NVL (a.postalcode, '0'), 10, ' ')
          || LPAD (NVL (a.currentacno, '0'), 18, ' ')
          || LPAD (NVL (a.chequeno, 0), 10, '0')
          || LPAD (NVL (a.rejectedchequeamount, 0), 15, '0')
          || CASE
                WHEN a.chequedate IS NOT NULL
                THEN
                   TO_CHAR (a.chequedate,
                            'YYYYMMDD',
                            'nls_calendar= persian')
                ELSE
                   RPAD (' ', 8, ' ')
             END
          || CASE
                WHEN a.chequerejecteddate IS NOT NULL
                THEN
                   TO_CHAR (a.chequerejecteddate,
                            'YYYYMMDD',
                            'nls_calendar= persian')
                ELSE
                   RPAD (' ', 8, ' ')
             END
          || LPAD (NVL (a.currencycode, ' '), 3, ' ')
          || LPAD (NVL (a.currencyrate, '0'), 6, '0')
     INTO v_res
     FROM RETURNCHEQUESCBI a
    WHERE requestSendId = inprequestId;

   RETURN v_res;
END;
/